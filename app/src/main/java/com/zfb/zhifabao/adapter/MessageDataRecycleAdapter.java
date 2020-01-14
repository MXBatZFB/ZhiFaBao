package com.zfb.zhifabao.adapter;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.factory.model.api.message.GetMessageResultModel;
import com.zfb.zhifabao.common.widget.cyclerview.RecyclerAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import androidx.annotation.NonNull;

public class MessageDataRecycleAdapter extends RecyclerAdapter<GetMessageResultModel.NoticeListBean> {
    @Override
    protected int getItemViewType(int position, GetMessageResultModel.NoticeListBean noticeListBean) {
        switch (noticeListBean.getType()){
            case 1://表示审合同的消息
                return R.layout.cell_review_msg;
            case 2://活动消息
                return R.layout.cell_activity_msg;
            default: //表示通用消息
                return R.layout.cell_common_msg;
        }
    }
    @Override
    protected ViewHolder<GetMessageResultModel.NoticeListBean> onCreateViewHolder(View root, int viewType) {
        switch (viewType){
            case R.layout.cell_common_msg:
                return new CommonMsgViewHolder(root);
            case R.layout.cell_review_msg:
                return new ReViewContractMsgViewHolder(root);
            case R.layout.cell_activity_msg:
                return new ActivityMsgViewHolder(root);
            default:
                return null;
        }
    }

    class CommonMsgViewHolder extends RecyclerAdapter.ViewHolder<GetMessageResultModel.NoticeListBean>{
        private TextView tvTitle;
        private TextView tvTime;
        private TextView tvContent;

        public CommonMsgViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvTime = itemView.findViewById(R.id.tvTime);
        }

        @Override
        protected void onBind(GetMessageResultModel.NoticeListBean noticeListBean) {
            tvContent.setText(noticeListBean.getContent());
            tvTitle.setText(noticeListBean.getTitle());
            SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
            Date date = new Date(Long.parseLong(noticeListBean.getNoticeDate()));
            String time = format.format(date);
            tvTime.setText(time);
        }
    }

    class ActivityMsgViewHolder extends RecyclerAdapter.ViewHolder<GetMessageResultModel.NoticeListBean>{
        private ImageView imPic;
        public ActivityMsgViewHolder(@NonNull View itemView) {
            super(itemView);
            imPic = itemView.findViewById(R.id.im_title_pic);
        }

        @Override
        protected void onBind(GetMessageResultModel.NoticeListBean noticeListBean) {
            Glide.with(Application.getInstance()).load(R.drawable.message_banner).into(imPic);
        }
    }

    class ReViewContractMsgViewHolder extends RecyclerAdapter.ViewHolder<GetMessageResultModel.NoticeListBean>{
        private TextView tvTitle;
        private TextView tvTime;
        private TextView tvContent;
        private FrameLayout flFind;

        public ReViewContractMsgViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvTime = itemView.findViewById(R.id.tvTime);
            flFind = itemView.findViewById(R.id.fl_find);
            flFind.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO
                }
            });
        }

        @Override
        protected void onBind(GetMessageResultModel.NoticeListBean noticeListBean) {
            tvContent.setText(noticeListBean.getContent());
            tvTitle.setText(noticeListBean.getTitle());
            SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
            Date date = new Date(Long.parseLong(noticeListBean.getNoticeDate()));
            String time = format.format(date);
            tvTime.setText(time);
        }
    }

}
