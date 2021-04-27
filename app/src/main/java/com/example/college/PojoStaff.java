package com.example.college;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PojoStaff {

    @SerializedName("stdname")
    @Expose
    public String stdname;
    @SerializedName("rollnum")
    @Expose
    private String rollnum;
    @SerializedName("dept")
    @Expose
    private String dept;
    @SerializedName("password_")
    @Expose
    private String password_;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("post")
    @Expose
    private String post;
    @SerializedName("coyear")
    @Expose
    private String coyear;
    @SerializedName("cosec")
    @Expose
    private String cosec;
    @SerializedName("message")
    @Expose
    private String message;



    public PojoStaff(String stdname, String rollnum, String dept, String password_, String email, String post) {
        this.stdname = stdname;
        this.rollnum = rollnum;
        this.dept = dept;
        this.password_ = password_;
        this.email = email;
        this.post = post;
    }

    public PojoStaff(String stdname, String rollnum, String dept, String password_, String email, String post, String coyear, String cosec) {
        this.stdname = stdname;
        this.rollnum = rollnum;
        this.dept = dept;
        this.password_ = password_;
        this.email = email;
        this.post = post;
        this.coyear = coyear;
        this.cosec = cosec;
    }

    public String getCoyear() {
        return coyear;
    }

    public void setCoyear(String coyear) {
        this.coyear = coyear;
    }

    public String getCosec() {
        return cosec;
    }

    public void setCosec(String cosec) {
        this.cosec = cosec;
    }

    public String getStdname() {
        return stdname;
    }

    public void setStdname(String stdname) {
        this.stdname = stdname;
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

    public String getPassword_() {
        return password_;
    }

    public void setPassword_(String password_) {
        this.password_ = password_;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
