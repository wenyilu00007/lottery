package com.wyl.lottery.weixin.api.messageapis.bean.messages;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 图文消息.
 *
 * @author Tony
 * @date 2018 -11-01 16:26:15
 */
@XStreamAlias("xml")
@Data
public class GraphicMessage extends BaseMessage {
    /**
     * The Article count.
     */
    @XStreamAlias("ArticleCount")
    private Integer ArticleCount;
    /**
     * The Articles.
     */
    @XStreamAlias("Articles")
    private List<GraphicArticle> articles = new ArrayList<>();

    public GraphicArticle createArticle() {
        GraphicArticle graphicArticle = new GraphicArticle();
        articles.add(graphicArticle);
        return graphicArticle;
    }

    /**
     * 图文消息文章
     *
     * @author Tony
     * @date 2018 -11-01 16:31:37
     */
    @XStreamAlias("item")
    @Data
    public static class GraphicArticle {
        /**
         * 图文消息标题
         */
        @XStreamAlias("Title")
        private String title;
        /**
         * 图文消息描述
         */
        @XStreamAlias("Description")
        private String description;
        /**
         * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
         */
        @XStreamAlias("PicUrl")
        private String picUrl;
        /**
         * 点击图文消息跳转链接
         */
        @XStreamAlias("Url")
        private String url;
    }
}


