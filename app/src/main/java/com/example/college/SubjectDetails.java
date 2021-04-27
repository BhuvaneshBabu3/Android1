package com.example.college;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubjectDetails {
    @SerializedName("Subjectname")
    @Expose
    public String Subjectname;
    @SerializedName("subjectcode")
    @Expose
    private String subjectcode;
    @SerializedName("dept")
    @Expose
    private String dept;
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("regulation")
    @Expose
    private String regulation;
    @SerializedName("id")
    @Expose
    private String id;
    public SubjectDetails(String subjectname, String subjectcode, String dept, String regulation) {
        Subjectname = subjectname;
        this.subjectcode = subjectcode;
        this.dept = dept;
        this.regulation=regulation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegulation() {
        return regulation;
    }

    public void setRegulation(String regulation) {
        this.regulation = regulation;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getSubjectname() {
        return Subjectname;
    }

    public void setSubjectname(String subjectname) {
        Subjectname = subjectname;
    }

    public String getSubjectcode() {
        return subjectcode;
    }

    public void setSubjectcode(String subjectcode) {
        this.subjectcode = subjectcode;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
