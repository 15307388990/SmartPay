package com.ming.smartpay.presenter;

import com.ming.smartpay.base.presenter.MvpPresenter;
import com.ming.smartpay.base.utils.TextUtil;
import com.ming.smartpay.base.widget.ToastShow;
import com.ming.smartpay.bean.ImgTab;
import com.ming.smartpay.bean.ProjectTab;
import com.ming.smartpay.view.modelview.HomeView;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class HomePresenter extends MvpPresenter<HomeView> {

    /**
     * 查询所有项目列表
     */
    public void showDate() {
        BmobQuery<ProjectTab> bmobQuery = new BmobQuery<>();
        bmobQuery.findObjects(new FindListener<ProjectTab>() {
            @Override
            public void done(List<ProjectTab> list, BmobException e) {

                getView().showData(list);
            }
        });
    }

    /**
     * 新增项目
     */
    public void AddProject(String name) {
        if (TextUtil.isEmpty(name)) {
            ToastShow.s("项目名称不能为空");
            return;
        }

        ProjectTab projectTab = new ProjectTab();
        projectTab.setPrijectName(name);
        projectTab.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    ToastShow.s("添加数据成功");
                    //更新数据
                    showDate();
                } else {
                    ToastShow.s("创建数据失败：" + e.getMessage());
                }
            }
        });
    }

    /**
     * 修改项目
     */
    public void UpdateAll(final String objectid, final boolean isEnable) {
        if (isEnable) {
            // 如果是打开一个开关就先把其它的都关闭了
            BmobQuery<ProjectTab> bmobQuery = new BmobQuery<>();
            bmobQuery.findObjects(new FindListener<ProjectTab>() {
                @Override
                public void done(List<ProjectTab> list, BmobException e) {
                    for (ProjectTab projectTab : list) {
                        projectTab.setEnable(false);
                        projectTab.update(projectTab.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {

                            }
                        });
                    }
                    Updata(objectid,isEnable);
                }
            });


        }else {
            Updata(objectid,isEnable);
        }


    }

    private void Updata(String objectid, boolean isEnable) {
        ProjectTab projectTab = new ProjectTab();
        projectTab.setEnable(isEnable);
        projectTab.update(objectid, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    showDate();
                } else {
                    ToastShow.s("更新失败：" + e.getMessage());
                }

            }
        });

    }
}
