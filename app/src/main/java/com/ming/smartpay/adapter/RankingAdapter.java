//package com.ming.smartpay.adapter;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.ming.smartpay.R;
//
//import java.util.List;
//
///**
// * @author luoming
// * created at 2019/8/27 6:09 PM
// */
//
//public class RankingAdapter extends BaseQuickAdapter<RankingBean,BaseViewHolder> {
//
//
//    public RankingAdapter(List<RankingBean> data) {
//        super(R.layout.ranking_item, data);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder baseViewHolder, RankingBean rankingBean) {
//        baseViewHolder.setText(R.id.tv_id, rankingBean.getId())
//                .setText(R.id.tv_ranking, rankingBean.getRanking())
//                .setText(R.id.tv_results, rankingBean.getResults());
//
//    }
//}
