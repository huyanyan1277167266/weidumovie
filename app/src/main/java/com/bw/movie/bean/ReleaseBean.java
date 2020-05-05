package com.bw.movie.bean;

import java.util.List;

/*
 *@Auther:cln
 *@Date: 2020/4/23
 *@Time:23:12
 *@Description:
 * */public class ReleaseBean {

    /**
     * result : [{"director":"庄文强","imageUrl":"http://172.17.8.100/images/movie/stills/ws/ws1.jpg","movieId":20,"name":"无双","score":8.6,"starring":"周润发,郭富城,张静初,冯文娟,廖启智"},{"director":"贾樟柯","imageUrl":"http://172.17.8.100/images/movie/stills/jhen/jhen1.jpg","movieId":19,"name":"江湖儿女","score":9.7,"starring":"赵涛,廖凡,徐峥,梁嘉艳"},{"director":"钱嘉乐","imageUrl":"http://172.17.8.100/images/movie/stills/hjxd/hjxd1.jpg","movieId":18,"name":"黄金兄弟","score":9.3,"starring":"郑伊健,陈小春,谢天华,林晓峰"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }


}
