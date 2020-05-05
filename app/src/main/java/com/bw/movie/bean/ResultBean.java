package com.bw.movie.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/*
 *@Auther:cln
 *@Date: 2020/4/29
 *@Time:22:37
 *@Description:
 * */
  @Entity
public class ResultBean {
    /**
     * director : 庄文强
     * imageUrl : http://172.17.8.100/images/movie/stills/ws/ws1.jpg
     * movieId : 20
     * name : 无双
     * score : 8.6
     * starring : 周润发,郭富城,张静初,冯文娟,廖启智
     */

    private String director;
    private String imageUrl;
    @Id
    private Long movieId;
    private String name;
    private double score;
    private String starring;

    @Generated(hash = 89965192)
    public ResultBean(String director, String imageUrl, Long movieId, String name,
            double score, String starring) {
        this.director = director;
        this.imageUrl = imageUrl;
        this.movieId = movieId;
        this.name = name;
        this.score = score;
        this.starring = starring;
    }

    @Generated(hash = 2137771703)
    public ResultBean() {
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }
}
