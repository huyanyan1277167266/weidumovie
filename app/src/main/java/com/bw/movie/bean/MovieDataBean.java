package com.bw.movie.bean;

/*
 *@Auther:cln
 *@Date: 2020/5/11
 *@Time:2:26
 *@Description:
 * */public class MovieDataBean {
    private ResultBean_movieinfo result;
    private String message;
    private String status;

    public ResultBean_movieinfo getResult() {
        return result;
    }

    public void setResult(ResultBean_movieinfo result) {
        this.result = result;
    }

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
