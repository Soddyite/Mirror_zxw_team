package com.example.dllo.mirror.model.bean;

/**
 * Created by dllo on 16/6/15.
 */
public class UserBean {


    /**
     * result : 1
     * msg :
     * data : {"token":"e65d201e9282576ce38dae45392dbd8f","uid":"98"}
     */

    private String result;
    private String msg;
    /**
     * token : e65d201e9282576ce38dae45392dbd8f
     * uid : 98
     */

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

    public static class DataBean {
        private String token;
        private String uid;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
