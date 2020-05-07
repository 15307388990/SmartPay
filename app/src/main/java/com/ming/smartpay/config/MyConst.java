package com.ming.smartpay.config;

/**
 * @author MyConst 请求地址 常量
 */
public class MyConst {
    /**
     * 微信模块参数
     */
    public static final String APP_ID = "wx89dce52534040293";
    public static final String APP_SECRET = "0bbfdeaa90461078d380c91deede2d15";
    public static final String GET_WECHAT_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
    public static final String GET_WECHAT_USER_INFO = "https://api.weixin.qq.com/sns/userinfo";


    /**
     * 主地址
     */
    public static final String BASE_URL = "http://47.57.19.48:80";

    /**
     * 登录
     */
    public static final String dologin = "/api/dologin";

    /**
     * 首页获取订单列表-【初始化接口】
     */
    public static final String getneworder = "/api/home/getneworder";
    /**
     * 首页获取排队位置
     */
    public static final String getqrline = "/api/home/getqrline";
    /**
     * 首页开始排队
     */
    public static final String insertqrline = "/api/home/insertqrline";
    /**
     * 获取二维码列表
     */
    public static final String getqrlist = "/api/qrmanage/getqrlist";
    /**
     * 退出排队
     */
    public static final String outqrline = "/api/home/outqrline";
    /**
     * 接受按钮(倒计时弹窗按钮)
     */
    public static final String accept = "/api/home/accept";
    /**
     * 绑定长链
     */
    public static final String binduid = "/api/service/binduid";
    /**
     * 确认到账
     */
    public static final String confirm = "/api/home/confirm";
    /**
     * 上传二维码
     */
    public static final String qrupload = "/api/qrmanage/qrupload";
    /**
     * 获取通道列表
     */
    public static final String getpaymentslist = "/api/qrmanage/getpaymentslist";
    /**
     * 二维码详情
     */
    public static final String getqrinfo = "/api/qrmanage/getqrinfo";
    /**
     * 删除二维码
     */
    public static final String deleteqr = "/api/qrmanage/deleteqr";
    /**
     * 启动(关闭)二维码状态
     */
    public static final String updataqrstatus = "/api/qrmanage/updataqrstatus";
    /**
     * 统计订单数量-【初始化接口】
     */
    public static final String getstatistics = "/api/orders/getstatistics";
    /**
     * 获取已结订单
     */
    public static final String getorderslist = "/api/orders/getorderslist";
    /**
     * 获取未结订单
     */
    public static final String getunfinishedlist = "/api/orders/getunfinishedlist";
    /**
     * 搜索订单
     */
    public static final String searchorder = "/api/orders/searchorder";
    /**
     * 获取用户信息
     */
    public static final String getuserinfo = "/api/my/getuserinfo";
    /**
     * 退出登录
     */
    public static final String outuser = "/api/my/outuser";
    /**
     * 修改密码
     */
    public static final String updatepassword = "/api/my/updatepassword";

    /**
     * 检查版本
     */
    public static final String version = "/api/version";
    /**
     * 获取订单详情
     */
    public static final String getdetails = "/api/home/getdetails";
    /**
     * 确认返款
     */
    public static final String confirmrefunds = "/api/home/confirmrefunds";
    /**
     * 判断是否已接受
     */
    public static final String is_accept = "/api/home/is_accept";
    /**
     * 检测推送订单（是否接收成功）
     */
    public static final String is_notice = "/api/home/is_notice";

}
