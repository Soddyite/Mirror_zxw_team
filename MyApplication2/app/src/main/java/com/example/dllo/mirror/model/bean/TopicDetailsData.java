package com.example.dllo.mirror.model.bean;

import java.util.List;

/**
 * Created by zouliangyu on 16/6/16.
 */
public class TopicDetailsData {

    /**
     * data : [{"id":"67165","title":"韩服钻4小学生 玩LOL竟是父亲的决定！","desc":"他的亚索堪比当年巅峰的Pawn和Dade！","video_url":"","published":1466052008,"weight":"0","platform":"1","pic_url":"","recommend":0,"w":123},{"id":"67167","title":"关晓彤晒毕业照 韦神竟然上热搜？","desc":"excuse me?","video_url":"","published":1466049600,"weight":"0","platform":"1","pic_url":"","recommend":0,"w":123},{"id":"67163","title":"若风Miss直播双排 人类精灵与巨魔","desc":"路漫漫其修远兮，风将上下而求M","video_url":"","published":1466042916,"weight":"0","platform":"1","pic_url":"","recommend":0,"w":123},{"id":"67152","title":"大小姐的贴身撸神 155","desc":"我擦，三年不见了，陈耀荣都结婚了？","video_url":"","published":1466024400,"weight":"0","platform":"1","pic_url":"","recommend":0,"w":123},{"id":"67153","title":"英雄联盟之不死传说 48","desc":"刘强叹了口气说：\u201c你昨天不是说你不喜欢菲菲么？\u201d","video_url":"","published":1466024400,"weight":"0","platform":"1","pic_url":"","recommend":0,"w":123},{"id":"67145","title":"作为一个坑玩LOL时的心理变化","desc":"宝宝虽然坑，但是宝宝萌啊！","video_url":"","published":1465990200,"weight":"0","platform":"1","pic_url":"","recommend":0,"w":123},{"id":"67151","title":"每日一瞎：有才撸友线勾英雄 萌哭辣","desc":"大家记得帮我报警","video_url":"","published":1465988400,"weight":"0","platform":"1","pic_url":"","recommend":0,"w":123},{"id":"67150","title":"大吐槽：怎么处罚阿怡大家才会满意？","desc":"很多人对阿怡的道歉并不买账，认为她脸皮太厚。","video_url":"","published":1465986628,"weight":"0","platform":"1","pic_url":"","recommend":0,"w":123},{"id":"67149","title":"男友沉迷LOL不找工作 霸气妹子怒砸电脑","desc":"妹纸：现在感觉好爽，现在别说电脑，WIFI他都别想用了！","video_url":"","published":1465984800,"weight":"0","platform":"1","pic_url":"","recommend":0,"w":123},{"id":"67132","title":"那些冷门又被版本遗忘 但依旧强势的英雄","desc":"他们不知道版本是什么，但是高胜率就是他们的特点！","video_url":"","published":1465974025,"weight":"0","platform":"1","pic_url":"","recommend":0,"w":123}]
     * code : 200
     * message : ok
     * api : 1
     */

    private int code;
    private String message;
    private int api;
    /**
     * id : 67165
     * title : 韩服钻4小学生 玩LOL竟是父亲的决定！
     * desc : 他的亚索堪比当年巅峰的Pawn和Dade！
     * video_url :
     * published : 1466052008
     * weight : 0
     * platform : 1
     * pic_url :
     * recommend : 0
     * w : 123
     */

    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getApi() {
        return api;
    }

    public void setApi(int api) {
        this.api = api;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String title;
        private String desc;
        private String video_url;
        private int published;
        private String weight;
        private String platform;
        private String pic_url;
        private int recommend;
        private int w;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public int getPublished() {
            return published;
        }

        public void setPublished(int published) {
            this.published = published;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public int getRecommend() {
            return recommend;
        }

        public void setRecommend(int recommend) {
            this.recommend = recommend;
        }

        public int getW() {
            return w;
        }

        public void setW(int w) {
            this.w = w;
        }
    }
}
