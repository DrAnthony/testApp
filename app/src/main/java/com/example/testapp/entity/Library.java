package com.example.testapp.entity;

public class Library {
    private Integer id;
    private String libName;
    private Integer floorNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibName() {
        return libName;
    }

    public void setLibName(String libName) {
        this.libName = libName;
    }

    public Integer getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(Integer floorNum) {
        this.floorNum = floorNum;
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", libName='" + libName + '\'' +
                ", floorNum=" + floorNum +
                '}';
    }
}
