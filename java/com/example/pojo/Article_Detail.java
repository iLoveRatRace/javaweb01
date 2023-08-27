package com.example.pojo;

/**
 * @author xiaojin
 * @version 1.0
 */
public class Article_Detail {
    private String id;
    private String detail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Article_detail{" +
                "id='" + id + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
