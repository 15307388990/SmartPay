package com.ming.smartpay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener;
import com.ming.smartpay.R;
import com.ming.smartpay.adapter.HomeAdapter;
import com.ming.smartpay.adapter.StationAdapter;
import com.ming.smartpay.base.activity.MvpActivity;
import com.ming.smartpay.base.cache.ACache;
import com.ming.smartpay.base.utils.AppUtils;
import com.ming.smartpay.base.utils.StatusBarUtil;
import com.ming.smartpay.base.widget.ToastShow;
import com.ming.smartpay.bean.StationTab;
import com.ming.smartpay.dialogfrment.AddStationDialog;
import com.ming.smartpay.dialogfrment.BtnDialog;
import com.ming.smartpay.presenter.StationPresenter;
import com.ming.smartpay.view.modelview.StationView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author luoming
 * created at 2019/8/19 9:22 AM
 * 工位管理
 */
public class StationActivity extends MvpActivity<StationView, StationPresenter> implements StationView, BtnDialog.OnClickListener {
    public static String PROJECTID = "ProjectId";
    public static String PROJECTNAME = "ProjectName";
    @BindView(R.id.ll_add)
    TextView llAdd;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private String ProjectId;
    private String ProjectName;

    private StationTab stationTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProjectId = getIntent().getStringExtra(PROJECTID);
        ProjectName = getIntent().getStringExtra(PROJECTNAME);
        setContentView(R.layout.activity_station, "项目" + ProjectName + "工位列表");
        StatusBarUtil.setColor(this, AppUtils.getColor(R.color.white));
        StatusBarUtil.setDarkMode(this);
        onClick();


    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.showDate(ProjectId);
    }

    private void onClick() {
        llAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddStationDialog.newInstance().setOnClickListener(new AddStationDialog.OnClickListener() {

                    @Override
                    public void successful(String obiectid, String name, String device, String imgUrl) {
                        mPresenter.save(name, device, ProjectId, imgUrl);
                    }

                }).show(StationActivity.this);
            }
        });
    }

    @Override
    protected StationPresenter createPresenter() {
        return new StationPresenter();
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
    public void showDate(final List<StationTab> stationTabs) {
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        StationAdapter stationAdapter = new StationAdapter(stationTabs);
        stationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (view.getId() == R.id.tv_id) {
                    Intent intent = new Intent(StationActivity.this, PreviewActivity.class);
                    intent.putExtra("imgurl", stationTabs.get(i).getImgUrl());
                    startActivity(intent);
                }
            }
        });
        stationAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                BtnDialog.newInstance().setOnClickListener(StationActivity.this).show(StationActivity.this);
                stationTab = stationTabs.get(position);
                return false;
            }
        });
        recyclerview.setAdapter(stationAdapter);
    }


    @Override
    public void update() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("station", stationTab);
        AddStationDialog.newInstance(bundle).setOnClickListener(new AddStationDialog.OnClickListener() {
            @Override
            public void successful(String obiectid, String name, String device, String imgUrl) {
                mPresenter.update(obiectid, name, device, ProjectId, imgUrl);
            }
        }).show(StationActivity.this);

    }

    @Override
    public void delete() {
        mPresenter.delete(stationTab.getObjectId(), ProjectId);


    }
}
