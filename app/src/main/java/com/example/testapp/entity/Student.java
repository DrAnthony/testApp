package com.example.testapp.entity;

public class Student {
    private Integer id;

    private String stuId;

    private Boolean stuGender;

    private String stuName;

    private School stuSchool;

    private String stuTel;

    private String stuPwd;

    private Integer stuGrade;

    private Boolean stuStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId == null ? null : stuId.trim();
    }

    public Boolean getStuGender() {
        return stuGender;
    }

    public void setStuGender(Boolean stuGender) {
        this.stuGender = stuGender;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }

    public School getStuSchool() {
        return stuSchool;
    }

    public void setStuSchool(School stuSchool) {
        this.stuSchool = stuSchool;
    }

    public String getStuTel() {
        return stuTel;
    }

    public void setStuTel(String stuTel) {
        this.stuTel = stuTel == null ? null : stuTel.trim();
    }

    public String getStuPwd() {
        return stuPwd;
    }

    public void setStuPwd(String stuPwd) {
        this.stuPwd = stuPwd == null ? null : stuPwd.trim();
    }

    public Integer getStuGrade() {
        return stuGrade;
    }

    public void setStuGrade(Integer stuGrade) {
        this.stuGrade = stuGrade;
    }

    public Boolean getStuStatus() {
        return stuStatus;
    }

    public void setStuStatus(Boolean stuStatus) {
        this.stuStatus = stuStatus;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuId='" + stuId + '\'' +
                ", stuGender=" + stuGender +
                ", stuName='" + stuName + '\'' +
                ", stuSchool=" + stuSchool +
                ", stuTel='" + stuTel + '\'' +
                ", stuPwd='" + stuPwd + '\'' +
                ", stuGrade=" + stuGrade +
                ", stuStatus=" + stuStatus +
                '}';
    }
}
