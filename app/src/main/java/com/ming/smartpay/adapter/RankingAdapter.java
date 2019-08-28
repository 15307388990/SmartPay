package com.ming.smartpay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ming.smartpay.R;
import com.ming.smartpay.bean.RankingBean;

import java.util.List;

/**
 * @author luoming
 * created at 2019/8/27 6:09 PM
 */

public class RankingAdapter extends BaseQuickAdapter<RankingBean> {


    public RankingAdapter(Context context, List<RankingBean> data) {
        super(context, R.layout.ranking_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RankingBean rankingBean) {
        baseViewHolder.setText(R.id.tv_id, rankingBean.getId())
                .setText(R.id.tv_ranking, rankingBean.getRanking())
                .setText(R.id.tv_results, rankingBean.getResults())
                .setImageUrl(R.id.iv_img, rankingBean.getImgurl());

    }
}
