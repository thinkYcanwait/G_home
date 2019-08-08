package it.gong.yunge.domain;

import java.sql.Timestamp;

public class Article {
    private int id;
    private String articlename;
    private int writer_id;
    private int readcount;
    private int likecount;
    private Timestamp writetime;
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticlename() {
        return articlename;
    }

    public void setArticlename(String arciclename) {
        this.articlename = arciclename;
    }

    public int getWriter_id() {
        return writer_id;
    }

    public void setWriter_id(int weiter_id) {
        this.writer_id = weiter_id;
    }

    public int getReadcount() {
        return readcount;
    }

    public void setReadcount(int readcount) {
        this.readcount = readcount;
    }

    public int getLikecount() {
        return likecount;
    }

    public void setLikecount(int likecount) {
        this.likecount = likecount;
    }

    public Timestamp getWritetime() {
        return writetime;
    }

    public void setWritetime(Timestamp writetime) {
        this.writetime = writetime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
