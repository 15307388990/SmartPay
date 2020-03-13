package com.ming.smartpay.ws;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;

import com.ming.smartpay.appication.SmartpayApplication;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WsManager {

    private static WsManager mInstance;
    private final String TAG = "WsManager";

    /**
     * WebSocket config
     */
    private static final int FRAME_QUEUE_SIZE = 5;
    private static final int CONNECT_TIMEOUT = 500000;
    private static final String DEF_RELEASE_URL = "ws://47.75.5.205:8585";//正式服默认地址
    private String url;

    private WsStatus mStatus;
    private WebSocket ws;
    private WsListener mListener;
    private IWsManagerActivityView iWsManagerActivityView;
    private boolean isConnection = true;

    public interface IWsManagerActivityView {
        void onTextMessage(String text);

        void onDisconnected();

        void onConnected();

    }

    private WsManager() {
    }

    public static WsManager getInstance() {

        if (mInstance == null) {
            synchronized (WsManager.class) {
                if (mInstance == null) {
                    mInstance = new WsManager();
                }
            }
        }
        return mInstance;
    }

    public WsManager setmListener(IWsManagerActivityView mListener) {
        this.iWsManagerActivityView = mListener;
        return mInstance;
    }

    public void init() {
        try {
            /**
             * configUrl其实是缓存在本地的连接地址
             * 这个缓存本地连接地址是app启动的时候通过http请求去服务端获取的,
             * 每次app启动的时候会拿当前时间与缓存时间比较,超过6小时就再次去服务端获取新的连接地址更新本地缓存
             */
            String configUrl = DEF_RELEASE_URL;
            url = configUrl;
            ws = new WebSocketFactory().createSocket(url, CONNECT_TIMEOUT)
                    .setFrameQueueSize(FRAME_QUEUE_SIZE)//设置帧队列最大值为5
                    .setMissingCloseFrameAllowed(false)//设置不允许服务端关闭连接却未发送关闭帧
                    .addListener(mListener = new WsListener())//添加回调监听
                    .connectAsynchronously();//异步连接
            setStatus(WsStatus.CONNECTING);
            Log.d(TAG, "第一次连接");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 继承默认的监听空实现WebSocketAdapter,重写我们需要的方法
     * onTextMessage 收到文字信息
     * onConnected 连接成功
     * onConnectError 连接失败
     * onDisconnected 连接关闭
     */
    class WsListener extends WebSocketAdapter {
        @Override
        public void onTextMessage(WebSocket websocket, String text) throws Exception {
            super.onTextMessage(websocket, text);
            // String decodedString = new String(Base64.decode(text, Base64.DEFAULT));
            if (iWsManagerActivityView != null) {
                iWsManagerActivityView.onTextMessage(text);
            }
            Log.d(TAG, "返回的东西" + text);
        }


        @Override
        public void onConnected(WebSocket websocket, Map<String, List<String>> headers)
                throws Exception {
            super.onConnected(websocket, headers);
            Log.d(TAG, "连接成功");
            if (iWsManagerActivityView != null) {
                iWsManagerActivityView.onConnected();
            }
            setStatus(WsStatus.CONNECT_SUCCESS);
            cancelReconnect();
        }


        @Override
        public void onConnectError(WebSocket websocket, WebSocketException exception)
                throws Exception {
            super.onConnectError(websocket, exception);
            Log.d(TAG, "连接错误");
            iWsManagerActivityView.onDisconnected();
            if (iWsManagerActivityView != null) {
                setStatus(WsStatus.CONNECT_FAIL);
            }
            reconnect();
        }


        @Override
        public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer)
                throws Exception {
            super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
            iWsManagerActivityView.onDisconnected();
            Log.d(TAG, "断开连接");
            setStatus(WsStatus.CONNECT_FAIL);
            reconnect();
        }
    }

    private void setStatus(WsStatus status) {
        this.mStatus = status;
    }

    private WsStatus getStatus() {
        return mStatus;
    }

    public void disconnect() {
        if (ws != null) {
            isConnection = false;
            ws.disconnect();
            ws = null;
        }
    }

    private Handler mHandler = new Handler();

    private int reconnectCount = 0;//重连次数
    private long minInterval = 3000;//重连最小时间间隔
    private long maxInterval = 60000;//重连最大时间间隔


    public void reconnect() {
//        if (!isConnection) {
//            Log.d(TAG, "退出页面不重连了");
//            return;
//        }

        //这里其实应该还有个用户是否登录了的判断 因为当连接成功后我们需要发送用户信息到服务端进行校验
        //由于我们这里是个demo所以省略了
        if (ws != null &&
                !ws.isOpen() &&//当前连接断开了
                getStatus() != WsStatus.CONNECTING) {//不是正在重连状态
//            //重联之前先断开上次链接
//            ws.disconnect();
//            Log.d(TAG, "断开上次连接");
            reconnectCount++;
            setStatus(WsStatus.CONNECTING);
            long reconnectTime = minInterval;
            if (reconnectCount > 3) {
                url = url;
                long temp = minInterval * (reconnectCount - 2);
                reconnectTime = temp > maxInterval ? maxInterval : temp;
            }
            Log.d(TAG, "准备开始第" + reconnectCount + "+次重连,重连间隔" + reconnectTime + " -- url" + url);
            mHandler.postDelayed(mReconnectTask, reconnectTime);
        }
    }


    private Runnable mReconnectTask = new Runnable() {

        @Override
        public void run() {
            try {
                ws = new WebSocketFactory().createSocket(url, CONNECT_TIMEOUT)
                        .setFrameQueueSize(FRAME_QUEUE_SIZE)//设置帧队列最大值为5
                        .setMissingCloseFrameAllowed(false)//设置不允许服务端关闭连接却未发送关闭帧
                        .addListener(mListener = new WsListener())//添加回调监听
                        .connectAsynchronously();//异步连接

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };


    private void cancelReconnect() {
        reconnectCount = 0;
        mHandler.removeCallbacks(mReconnectTask);
    }


//    private boolean isNetConnect() {
//        ConnectivityManager connectivity = (ConnectivityManager) SmartpayApplication.getContext()
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivity != null) {
//            NetworkInfo info = connectivity.getActiveNetworkInfo();
//            if (info != null && info.isConnected()) {
//                // 当前网络是连接的
//                if (info.getState() == NetworkInfo.State.CONNECTED) {
//                    // 当前所连接的网络可用
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
}
