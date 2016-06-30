package com.example.dllo.mirror.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dllo on 16/6/28.
 */
public class GoodsDetails implements Serializable {

    private String result;
    private String msg;

    private DataBean data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * first_time : 1465989138
         * last_time : 1465956483
         * has_more : 2
         */

        private PaginationBean pagination;
        /**
         * type : 1
         * data_info : {"goods_id":"iOvQp1465978771","goods_pic":"http://image.mirroreye.cn/10d9f8c9d4c3f5e02065cd8c23f9af85a.jpg","model":"SS-S-01|晶粉貓眼太陽鏡","goods_img":"http://image.mirroreye.cn/SPRINGSTRINGSfensefubene14988b6e7b2e4451ffebb07305455d7.jpg","goods_name":" SPRING STRINGS","last_storge":"1","whole_storge":"1","height":"","ordain":"1","product_area":"韩国","goods_price":"1280","discount_price":"","brand":"女款｜晶粉貓眼太陽鏡","info_des":"来自韩国的眼镜潮牌SPRING STRINGS是现代与复古的完美承载体，以古典与怀旧为基本设计风格，再融合现代的流行元素，深受潮人们的青睐。\n猫眼型镜框设计感十足。既要典雅尊贵，又要时尚弄潮儿一般的摩登姿态，低调中展现别样风情，彰显独特魅力。\n","goods_data":[{"introContent":"作为韩国潮牌太阳镜，SPRING STRINGS被许多国际人士所热爱。包括韩国女团T-ARA,SISTAR以及各路来自全国各地的潮人都经常配戴，是时尚达人不可或缺的太阳镜。","cellHeight":"590","name":"","location":"來自時尚之都韓國","country":"韓國","english":"The frame comes from Korea"},{"introContent":"猫眼型镜框具有极其浓郁的复古气息，是各路女神十分偏爱的一款。而SPRING STRINGS\u201c少女感\u201d十足的水晶粉与oversize的造型设计又相当的时髦前卫。不愧为古典怀旧与现代流行的完美融合。","cellHeight":"590","name":"時髦復古","location":"","country":"","english":""},{"introContent":"这款猫眼太阳镜设计十分简约，用最流畅的线条与一丝不苟的质感表现出最直接而纯粹的美。极简设计太阳镜佩戴轻盈舒适，适合长时间佩戴。","cellHeight":"590","name":"極簡設計","location":"","country":"","english":""},{"introContent":"简约的设计除去了一切不必要的装饰，更使得整体佩戴体验更加轻盈舒适。柔软立体的鼻托使得五官更为精致，超大镜架十分衬托脸型。\n","cellHeight":"590","name":"舒適輕盈佩戴","location":"","country":"","english":""}],"design_des":[{"img":"http://image.mirroreye.cn/1f303e3a880ba784968b503c2fbd4da87.jpg","cellHeight":"480","type":"1"},{"img":"http://image.mirroreye.cn/23c3c983e5634a3e0379bcfc1070237db.jpg","cellHeight":"480","type":"1"},{"img":"http://image.mirroreye.cn/42a6618dc417c85e5174ca73a84a6ad1d.jpg","cellHeight":"480","type":"1"},{"img":"http://image.mirroreye.cn/3660d7c7f5f093b704e305d37c1554f32.jpg","cellHeight":"480","type":"1"},{"img":"http://image.mirroreye.cn/chicunbf9e59473fa94566740337693a57d92b.jpg","cellHeight":"550","type":"2"},{"img":"http://image.mirroreye.cn/shouhouxuzhi1d2feece96894c244d5e62de7ab6acc6e.jpg","cellHeight":"828","type":"3"}],"goods_share":"http://api.mirroreye.cn/index.php/goodweb/info?id=iOvQp1465978771"}
         */

        private List<ListBean> list;

        public PaginationBean getPagination() {
            return pagination;
        }

        public void setPagination(PaginationBean pagination) {
            this.pagination = pagination;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PaginationBean implements Serializable {
            private String first_time;
            private String last_time;
            private String has_more;

            public String getFirst_time() {
                return first_time;
            }

            public void setFirst_time(String first_time) {
                this.first_time = first_time;
            }

            public String getLast_time() {
                return last_time;
            }

            public void setLast_time(String last_time) {
                this.last_time = last_time;
            }

            public String getHas_more() {
                return has_more;
            }

            public void setHas_more(String has_more) {
                this.has_more = has_more;
            }
        }

        public static class ListBean implements Serializable {
            private String type;
            /**
             * goods_id : iOvQp1465978771
             * goods_pic : http://image.mirroreye.cn/10d9f8c9d4c3f5e02065cd8c23f9af85a.jpg
             * model : SS-S-01|晶粉貓眼太陽鏡
             * goods_img : http://image.mirroreye.cn/SPRINGSTRINGSfensefubene14988b6e7b2e4451ffebb07305455d7.jpg
             * goods_name :  SPRING STRINGS
             * last_storge : 1
             * whole_storge : 1
             * height :
             * ordain : 1
             * product_area : 韩国
             * goods_price : 1280
             * discount_price :
             * brand : 女款｜晶粉貓眼太陽鏡
             * info_des : 来自韩国的眼镜潮牌SPRING STRINGS是现代与复古的完美承载体，以古典与怀旧为基本设计风格，再融合现代的流行元素，深受潮人们的青睐。
             * 猫眼型镜框设计感十足。既要典雅尊贵，又要时尚弄潮儿一般的摩登姿态，低调中展现别样风情，彰显独特魅力。
             * <p/>
             * goods_data : [{"introContent":"作为韩国潮牌太阳镜，SPRING STRINGS被许多国际人士所热爱。包括韩国女团T-ARA,SISTAR以及各路来自全国各地的潮人都经常配戴，是时尚达人不可或缺的太阳镜。","cellHeight":"590","name":"","location":"來自時尚之都韓國","country":"韓國","english":"The frame comes from Korea"},{"introContent":"猫眼型镜框具有极其浓郁的复古气息，是各路女神十分偏爱的一款。而SPRING STRINGS\u201c少女感\u201d十足的水晶粉与oversize的造型设计又相当的时髦前卫。不愧为古典怀旧与现代流行的完美融合。","cellHeight":"590","name":"時髦復古","location":"","country":"","english":""},{"introContent":"这款猫眼太阳镜设计十分简约，用最流畅的线条与一丝不苟的质感表现出最直接而纯粹的美。极简设计太阳镜佩戴轻盈舒适，适合长时间佩戴。","cellHeight":"590","name":"極簡設計","location":"","country":"","english":""},{"introContent":"简约的设计除去了一切不必要的装饰，更使得整体佩戴体验更加轻盈舒适。柔软立体的鼻托使得五官更为精致，超大镜架十分衬托脸型。\n","cellHeight":"590","name":"舒適輕盈佩戴","location":"","country":"","english":""}]
             * design_des : [{"img":"http://image.mirroreye.cn/1f303e3a880ba784968b503c2fbd4da87.jpg","cellHeight":"480","type":"1"},{"img":"http://image.mirroreye.cn/23c3c983e5634a3e0379bcfc1070237db.jpg","cellHeight":"480","type":"1"},{"img":"http://image.mirroreye.cn/42a6618dc417c85e5174ca73a84a6ad1d.jpg","cellHeight":"480","type":"1"},{"img":"http://image.mirroreye.cn/3660d7c7f5f093b704e305d37c1554f32.jpg","cellHeight":"480","type":"1"},{"img":"http://image.mirroreye.cn/chicunbf9e59473fa94566740337693a57d92b.jpg","cellHeight":"550","type":"2"},{"img":"http://image.mirroreye.cn/shouhouxuzhi1d2feece96894c244d5e62de7ab6acc6e.jpg","cellHeight":"828","type":"3"}]
             * goods_share : http://api.mirroreye.cn/index.php/goodweb/info?id=iOvQp1465978771
             */

            private DataInfoBean data_info;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public DataInfoBean getData_info() {
                return data_info;
            }

            public void setData_info(DataInfoBean data_info) {
                this.data_info = data_info;
            }

            public static class DataInfoBean implements Serializable {
                private String goods_id;
                private String goods_pic;
                private String model;
                private String goods_img;
                private String goods_name;
                private String last_storge;
                private String whole_storge;
                private String height;
                private String ordain;
                private String product_area;
                private String goods_price;
                private String discount_price;
                private String brand;
                private String info_des;
                private String goods_share;
                private String story_title;
                private String story_img;
                private StoryDataBean story_data;

                public StoryDataBean getStory_data() {
                    return story_data;
                }

                public void setStory_data(StoryDataBean story_data) {
                    this.story_data = story_data;
                }

                public String getStory_title() {
                    return story_title;
                }

                public void setStory_title(String story_title) {
                    this.story_title = story_title;
                }

                public String getStory_img() {
                    return story_img;
                }

                public void setStory_img(String story_img) {
                    this.story_img = story_img;
                }

                /**
                 * introContent : 作为韩国潮牌太阳镜，SPRING STRINGS被许多国际人士所热爱。包括韩国女团T-ARA,SISTAR以及各路来自全国各地的潮人都经常配戴，是时尚达人不可或缺的太阳镜。
                 * cellHeight : 590
                 * name :
                 * location : 來自時尚之都韓國
                 * country : 韓國
                 * english : The frame comes from Korea
                 */

                private List<GoodsDataBean> goods_data;
                /**
                 * img : http://image.mirroreye.cn/1f303e3a880ba784968b503c2fbd4da87.jpg
                 * cellHeight : 480
                 * type : 1
                 */

                private List<DesignDesBean> design_des;

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

                public String getGoods_pic() {
                    return goods_pic;
                }

                public void setGoods_pic(String goods_pic) {
                    this.goods_pic = goods_pic;
                }

                public String getModel() {
                    return model;
                }

                public void setModel(String model) {
                    this.model = model;
                }

                public String getGoods_img() {
                    return goods_img;
                }

                public void setGoods_img(String goods_img) {
                    this.goods_img = goods_img;
                }

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public String getLast_storge() {
                    return last_storge;
                }

                public void setLast_storge(String last_storge) {
                    this.last_storge = last_storge;
                }

                public String getWhole_storge() {
                    return whole_storge;
                }

                public void setWhole_storge(String whole_storge) {
                    this.whole_storge = whole_storge;
                }

                public String getHeight() {
                    return height;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public String getOrdain() {
                    return ordain;
                }

                public void setOrdain(String ordain) {
                    this.ordain = ordain;
                }

                public String getProduct_area() {
                    return product_area;
                }

                public void setProduct_area(String product_area) {
                    this.product_area = product_area;
                }

                public String getGoods_price() {
                    return goods_price;
                }

                public void setGoods_price(String goods_price) {
                    this.goods_price = goods_price;
                }

                public String getDiscount_price() {
                    return discount_price;
                }

                public void setDiscount_price(String discount_price) {
                    this.discount_price = discount_price;
                }

                public String getBrand() {
                    return brand;
                }

                public void setBrand(String brand) {
                    this.brand = brand;
                }

                public String getInfo_des() {
                    return info_des;
                }

                public void setInfo_des(String info_des) {
                    this.info_des = info_des;
                }

                public String getGoods_share() {
                    return goods_share;
                }

                public void setGoods_share(String goods_share) {
                    this.goods_share = goods_share;
                }

                public List<GoodsDataBean> getGoods_data() {
                    return goods_data;
                }

                public void setGoods_data(List<GoodsDataBean> goods_data) {
                    this.goods_data = goods_data;
                }

                public List<DesignDesBean> getDesign_des() {
                    return design_des;
                }

                public void setDesign_des(List<DesignDesBean> design_des) {
                    this.design_des = design_des;
                }


                public static class StoryDataBean implements Serializable {
                    private List<String> img_array;
                    private List<TextArrayBean> text_array;

                    public List<String> getImg_array() {
                        return img_array;
                    }

                    public void setImg_array(List<String> img_array) {
                        this.img_array = img_array;
                    }

                    public List<TextArrayBean> getText_array() {
                        return text_array;
                    }

                    public void setText_array(List<TextArrayBean> text_array) {
                        this.text_array = text_array;
                    }
                }

                public static class TextArrayBean implements Serializable {
                    private String smallTitle;
                    private String title;
                    private String subTitle;

                    public String getSmallTitle() {
                        return smallTitle;
                    }

                    public void setSmallTitle(String smallTitle) {
                        this.smallTitle = smallTitle;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getSubTitle() {
                        return subTitle;
                    }

                    public void setSubTitle(String subTitle) {
                        this.subTitle = subTitle;
                    }
                }


                public static class GoodsDataBean implements Serializable {
                    private String introContent;
                    private String cellHeight;
                    private String name;
                    private String location;
                    private String country;
                    private String english;

                    public String getIntroContent() {
                        return introContent;
                    }

                    public void setIntroContent(String introContent) {
                        this.introContent = introContent;
                    }

                    public String getCellHeight() {
                        return cellHeight;
                    }

                    public void setCellHeight(String cellHeight) {
                        this.cellHeight = cellHeight;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getLocation() {
                        return location;
                    }

                    public void setLocation(String location) {
                        this.location = location;
                    }

                    public String getCountry() {
                        return country;
                    }

                    public void setCountry(String country) {
                        this.country = country;
                    }

                    public String getEnglish() {
                        return english;
                    }

                    public void setEnglish(String english) {
                        this.english = english;
                    }
                }

                public static class DesignDesBean implements Serializable {
                    private String img;
                    private String cellHeight;
                    private String type;

                    public String getImg() {
                        return img;
                    }

                    public void setImg(String img) {
                        this.img = img;
                    }

                    public String getCellHeight() {
                        return cellHeight;
                    }

                    public void setCellHeight(String cellHeight) {
                        this.cellHeight = cellHeight;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }
                }
            }
        }
    }
}
