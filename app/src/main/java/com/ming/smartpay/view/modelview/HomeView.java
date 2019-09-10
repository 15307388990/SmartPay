package com.ming.smartpay.view.modelview;

import com.ming.smartpay.base.view.MvpView;
import com.ming.smartpay.bean.ImgTab;
import com.ming.smartpay.bean.ProjectTab;

import java.util.List;

public interface HomeView extends MvpView {
    void showData(List<ProjectTab> projectTabs);

}
