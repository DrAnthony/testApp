package com.example.testapp.entity;

import java.util.Date;

public class Notice {
    private Integer id;
    private Admin admin;
    private Date createTime;
    private Library library;
    private String uri;
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", admin=" + admin +
                ", createTime=" + createTime +
                ", library=" + library +
                ", uri='" + uri + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
