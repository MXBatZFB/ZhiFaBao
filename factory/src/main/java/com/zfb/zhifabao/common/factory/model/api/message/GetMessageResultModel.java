package com.zfb.zhifabao.common.factory.model.api.message;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class GetMessageResultModel {


    private List<NoticeListBean> noticeList;

    public List<NoticeListBean> getNoticeList() {
        return noticeList;
    }

    public void setNoticeList(List<NoticeListBean> noticeList) {
        this.noticeList = noticeList;
    }

    public static class NoticeListBean implements Parcelable {
        /**
         * id : 4
         * type : 0
         * title : 会员到期提醒
         * content : 亲，你的会员将于2天后到期，请及时续费哦。
         * noticeDate : 1578552635000
         * keyword : null
         */

        private String id;
        private int type;
        private String title;
        private String content;
        private String noticeDate;

        public NoticeListBean(String id, int type, String title, String content, String noticeDate) {
            this.id = id;
            this.type = type;
            this.title = title;
            this.content = content;
            this.noticeDate = noticeDate;
        }

        protected NoticeListBean(Parcel source) {
            this.id = source.readString();
            this.type = source.readInt();
            this.title = source.readString();
            this.content = source.readString();
            this.noticeDate = source.readString();
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getNoticeDate() {
            return noticeDate;
        }

        public void setNoticeDate(String noticeDate) {
            this.noticeDate = noticeDate;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        /**
         * id : 4
         * type : 0:通用的 1.合同审核 2.活动
         * title : 会员到期提醒
         * content : 亲，你的会员将于2天后到期，请及时续费哦。
         * noticeDate : 1578552635000
         */
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeInt(this.type);
            dest.writeString(this.title);
            dest.writeString(this.content);
            dest.writeString(this.noticeDate);
        }


        public static final Parcelable.Creator<NoticeListBean> CREATOR = new Parcelable.Creator<NoticeListBean>() {
            @Override
            public NoticeListBean createFromParcel(Parcel source) {
                return new NoticeListBean(source);
            }

            @Override
            public NoticeListBean[] newArray(int size) {
                return new NoticeListBean[size];
            }
        };

    }
}
