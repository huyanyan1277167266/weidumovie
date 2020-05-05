package com.bw.movie.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.util.List;

/*
 *@Auther:cln
 *@Date: 2020/4/23
 *@Time:23:30
 *@Description:
 * */public class BannerBean {


    /**
     * result : [{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/whwdzg/whwdzg1_h.jpg","jumpUrl":"wd://movie?movieId=23","rank":1},{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/zgjz/zgjz1_h.jpg","jumpUrl":"wd://movie?movieId=24","rank":2},{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/pdz/pdz1_h.jpg","jumpUrl":"wd://movie?movieId=25","rank":3},{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/sndn/sndn1_h.jpg","jumpUrl":"wd://movie?movieId=22","rank":4},{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/dwsj/dwsj1_h.jpg","jumpUrl":"wd://movie?movieId=10","rank":5}]
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

    public static class ResultBean extends SimpleBannerInfo {
        /**
         * imageUrl : http://mobile.bwstudent.com/images/movie/stills/whwdzg/whwdzg1_h.jpg
         * jumpUrl : wd://movie?movieId=23
         * rank : 1
         */

        private String imageUrl;
        private String jumpUrl;
        private int rank;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getJumpUrl() {
            return jumpUrl;
        }

        public void setJumpUrl(String jumpUrl) {
            this.jumpUrl = jumpUrl;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        @Override
        public Object getXBannerUrl() {
            return null;
        }
    }
}
