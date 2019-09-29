package com.ming.smartpay.fragment.mian;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ming.smartpay.R;
import com.ming.smartpay.activity.StationActivity;
import com.ming.smartpay.adapter.HomeAdapter;
import com.ming.smartpay.base.fragment.MvpFragment;
import com.ming.smartpay.bean.ProjectTab;
import com.ming.smartpay.dialogfrment.AddProjectDialog;
import com.ming.smartpay.presenter.HomePresenter;
import com.ming.smartpay.view.WiperSwitch;
import com.ming.smartpay.view.modelview.HomeView;

import java.util.List;

import butterknife.BindView;


public class HomeFragemt extends MvpFragment<HomeView, HomePresenter> implements HomeView, BaseQuickAdapter.OnItemClickListener, HomeAdapter.OnChangedListener {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.ll_add)
    TextView llAdd;
    @BindView(R.id.btn_refresh)
    Button btnRefresh;

    private List<ProjectTab> projectTabs;

    public static HomeFragemt newInstance() {
        return new HomeFragemt();
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        setContentView(R.layout.fragemt_home);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.showDate();
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showData(List<ProjectTab> projectTabs) {
        this.projectTabs = projectTabs;
        if (projectTabs == null || projectTabs.size() < 1) {
            btnRefresh.setVisibility(View.VISIBLE);
            recyclerview.setVisibility(View.GONE);
        } else {
            btnRefresh.setVisibility(View.GONE);
            recyclerview.setVisibility(View.VISIBLE);
            recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            HomeAdapter homeAdapter = new HomeAdapter(projectTabs, this);
            homeAdapter.setOnItemClickListener(this);
            recyclerview.setAdapter(homeAdapter);
        }


        llAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddProjectDialog.newInstance().setOnClickListener(new AddProjectDialog.OnClickListener() {
                    @Override
                    public void successful(String name) {

                        mPresenter.AddProject(name);
                    }
                }).show(getActivity());
            }
        });
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.showDate();
            }
        });

    }


    @Override
    public void OnChanged(WiperSwitch wiperSwitch, boolean checkState, int position) {
        mPresenter.UpdateAll(projectTabs.get(position).getObjectId(), checkState);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ProjectTab projectTab = projectTabs.get(position);
        Intent intent = new Intent(getActivity(), StationActivity.class);
        intent.putExtra(StationActivity.PROJECTID, projectTab.getObjectId());
        intent.putExtra(StationActivity.PROJECTNAME, projectTab.getPrijectName());
        startActivity(intent);
    }
}
