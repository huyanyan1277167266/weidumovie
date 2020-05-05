package com.bw.movie.bean;

/*
 *@Auther:cln
 *@Date: 2020/4/22
 *@Time:11:24
 *@Description:
 * */public class EmailBean {


    /**
     * message : 发送成功
     * status : 0000
     */

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
