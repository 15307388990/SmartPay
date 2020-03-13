package com.ming.smartpay.fragment.mian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ming.smartpay.R;
import com.ming.smartpay.base.fragment.BaseFragment;
import com.ming.smartpay.view.CircleImageView;


import butterknife.BindView;

public class RankingFragemt extends BaseFragment {

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
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public View getLayoutRes() {
        return null;
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

//    @Override
//    public void setData(List<RankingBean> rankingBeans) {
//        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        recyclerview.setAdapter(new RankingAdapter( rankingBeans));
//        Glide.with(this).load("https://c-ssl.duitang.com/uploads/blog/201512/19/20151219212115_sxind.thumb.700_0.jpeg").into(civA);
//        Glide.with(this).load("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3146277763,293920617&fm=11&gp=0.jpg").into(civB);
//        Glide.with(this).load("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3276179142,1686381254&fm=26&gp=0.jpg").into(civC);
//    }
}
