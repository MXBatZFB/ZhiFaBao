package com.zfb.zhifabao.common.factory.model.api.find;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class FindContent {
    private String article_title;
    private String article_pic_link;
    private String article_link;

    public FindContent(String article_title, String article_pic_link, String article_link) {
        this.article_title = article_title;
        this.article_pic_link = article_pic_link;
        this.article_link = article_link;
    }

    @Override
    public String toString() {
        return "FindContent{" +
                "article_title='" + article_title + '\'' +
                ", article_pic_link='" + article_pic_link + '\'' +
                ", article_link='" + article_link + '\'' +
                '}';
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_pic_link() {
        return article_pic_link;
    }

    public void setArticle_pic_link(String article_pic_link) {
        this.article_pic_link = article_pic_link;
    }

    public String getArticle_link() {
        return article_link;
    }

    public void setArticle_link(String article_link) {
        this.article_link = article_link;
    }
}
