package com.example.college;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoStudent {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("studentname")
    @Expose
    public String studentname;
    @SerializedName("rollnum")
    @Expose
    private String rollnum;
    @SerializedName("dept")
    @Expose
    private String dept;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("emailid")
    @Expose
    private String emailid;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("sec")
    @Expose
    private String sec;
    @SerializedName("coordinator")
    @Expose
    private String coordinator;

    @SerializedName("response")
    @Expose
    private String response;


    public PojoStudent(String studentname, String rollnum, String dept, String phone, String emailid, String year, String sec) {
        this.studentname = studentname;
        this.rollnum = rollnum;
        this.dept = dept;
        this.phone = phone;
        this.emailid = emailid;
        this.year = year;
        this.sec = sec;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PojoStudent(String id) {
        rollnum=id;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getRollnum() {
        return rollnum;
    }

    public void setRollnum(String rollnum) {
        this.rollnum = rollnum;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }
}
