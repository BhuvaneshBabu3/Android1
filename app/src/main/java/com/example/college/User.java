package com.example.college;

public class User {
    int id;
    String name;
    String dept;
    String post;

    public User(int id, String name, String dept, String post) {
        this.id = id;
        this.name = name;
        this.dept=dept;
        this.post=post;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}

