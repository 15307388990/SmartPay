package com.ming.smartpay.fragment.mian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ming.smartpay.R;
import com.ming.smartpay.adapter.RankingAdapter;
import com.ming.smartpay.base.fragment.MvpFragment;
import com.ming.smartpay.bean.RankingBean;
import com.ming.smartpay.presenter.RankingPresenter;
import com.ming.smartpay.view.CircleImageView;
import com.ming.smartpay.view.modelview.RankingView;

import java.util.List;

import butterknife.BindView;

public class RankingFragemt extends MvpFragment<RankingView, RankingPresenter> implements RankingView {

    @BindView(R.id.iv_b)
    ImageView ivB;
    @BindView(R.id.iv_bb)
    ImageView ivBb;
    @BindView(R.id.tv_name_b)
    TextView tvNameB;
    @BindView(R.id.iv_a)
    ImageView ivA;
    @BindView(R.id.iv_aa)
    ImageView ivAa;
    @BindView(R.id.tv_name_a)
    TextView tvNameA;
    @BindView(R.id.iv_c)
    ImageView ivC;
    @BindView(R.id.iv_cc)
    ImageView ivCc;
    @BindView(R.id.tv_name_c)
    TextView tvNameC;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.civ_b)
    CircleImageView civB;
    @BindView(R.id.civ_a)
    CircleImageView civA;
    @BindView(R.id.civ_c)
    CircleImageView civC;

    public static RankingFragemt newInstance() {
        return new RankingFragemt();
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        setContentView(R.layout.fragemt_ranking);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.showDate();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected RankingPresenter createPresenter() {
        return new RankingPresenter();
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
    public void setData(List<RankingBean> rankingBeans) {
        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerview.setAdapter(new RankingAdapter( rankingBeans));
        Glide.with(this).load("https://c-ssl.duitang.com/uploads/blog/201512/19/20151219212115_sxind.thumb.700_0.jpeg").into(civA);
        Glide.with(this).load("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3146277763,293920617&fm=11&gp=0.jpg").into(civB);
        Glide.with(this).load("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3276179142,1686381254&fm=26&gp=0.jpg").into(civC);
    }
}
