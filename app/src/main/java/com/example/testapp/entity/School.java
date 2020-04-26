package com.example.testapp.entity;

public class School {
    private Integer id;

    private String schoolName;

    private String schoolDes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public String getSchoolDes() {
        return schoolDes;
    }

    public void setSchoolDes(String schoolDes) {
        this.schoolDes = schoolDes == null ? null : schoolDes.trim();
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", schoolName='" + schoolName + '\'' +
                ", schoolDes='" + schoolDes + '\'' +
                '}';
    }
}
