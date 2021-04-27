package com.example.college;

import com.example.college.Settings.ListPojoStudent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInteface {

    @Headers("Accept: application/json")
    @POST("/pjtcollage/product/insertStaffdata.php")
    Call<PojoStaff> insertStaff(@Body PojoStaff pojoStaff);

//    @Headers("Accept: application/json")
    @HTTP(method = "DELETE",path="/pjtcollage/product/deleteStudent.php",hasBody = true)
    Call<PojoStudent> deleteStudent(@Body PojoStudent pojoStudent);


    @Headers("Accept: application/json")
    @POST("/pjtcollage/product/insertstudentdetails.php")
    Call<PojoStudent> insertStudent(@Body PojoStudent pojoStudent);

   /* @Field("stdname") String stdname,
    @Field("rollnum") String rollnum,
    @Field("dept") String dept,
    @Field("password_") String password_,
    @Field("email") String email,
    @Field("post") String post,
    @Field("image_path") String image_path*/

    @GET("/pjtcollage/product/stafflogin.php?")
    Call<ListPojostaff> getstafflogin(@Query("rollnum") String rollnum);

    @GET("/pjtcollage/product/staffSpinner.php?")
    Call<ListPojostaff> getcoordinator(@Query("dept") String dept);

    @GET("/pjtcollage/product/getstaff.php?")
    Call<List<PojoStaff>> getstaff(@Query("dept") String dept);

    @GET("/pjtcollage/product/getSubjects.php?")
    Call<List<SubjectDetails>> getSubjects(@Query("dept") String dept);

    @GET("/pjtcollage/product/getStudentdetails.php?")
    Call<ArrayList<PojoStudent>> getStudentdetails(@Query("dept") String dept);

    @GET("/pjtcollage/product/getSubjectdetails.php")
    Call<ArrayList<SubjectDetails>> getSubjectdetails(@Query("dept") String dept);

    //Add subject details
    @Headers("Accept: application/json")
    @POST("/pjtcollage/product/insertSubject.php")
    Call<SubjectDetails> addSubject(@Body SubjectDetails subjectDetails);
}
