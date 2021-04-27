package com.example.college.Login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.college.Api;
import com.example.college.ApiInteface;
import com.example.college.PojoStaff;
import com.example.college.R;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {


    public static final int STORAGE_PERMISSION_CODE = 2342;
    private static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    public static final int PICK_IMAGE_REQUEST = 22;
    String departmentname, postname;
    private static final String[] department_arr = {"ECE", "EEE", "IT", "CSE", "AUTO", "MECH"};

    private static final String[] posting_arr = {"HOD", "MENDOR", "CO-ORDINATOR", "staff", "none"};
    public static final String[] staffyear={"I","II","III","IV"};
    public static final String[] staffSec={"A","B","C"};
    Bitmap image;
    int i=1;
    Spinner staff_post_year;
    ImageView staffimg;
    String currentphotopath,coyear,cosec;;
    File photoFile = null;

    private Uri filepath;
    private Bitmap bitmap;

    public final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 1;
    private int requestCode;
    private String[] permissions;
    private int[] grantResults;

    private static final String UPLOAD_URL="https://www.eceianstech.com/pjtcollage/product/uploads.php";
//requests for runtime time permissions


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextInputLayout name, rollnum, email, password, cnfmpassword;
        TextView choosetxtdept, choosetxtpost;
        Spinner spindept, spinpost;
        Button submitbtn, chooseimg;


        LinearLayout ll1=(LinearLayout)findViewById(R.id.ll1);
        LinearLayout ll2=(LinearLayout)findViewById(R.id.ll2);


        staff_post_year=(Spinner)findViewById(R.id.staff_post_year);
        Spinner staff_post_sec=(Spinner)findViewById(R.id.staff_post_sec);


        //textInputeditText
        name = findViewById(R.id.name);
        rollnum = findViewById(R.id.rollnum);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cnfmpassword = findViewById(R.id.cnfmpassword);

        //textView
        choosetxtdept = (TextView) findViewById(R.id.choosetxtdept);
        choosetxtpost = (TextView) findViewById(R.id.choosetxtpost);

        //spinner
         spindept = (Spinner) findViewById(R.id.spindept);
        spinpost = (Spinner) findViewById(R.id.spinpost);

        //Button
        submitbtn = (Button) findViewById(R.id.submitbtn);
        Button cancelbtn=(Button)findViewById(R.id.cancelbtn);
//        chooseimg = (Button) findViewById(R.id.chooseimg);

//        staffimg=(ImageView)findViewById(R.id.staffimg);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item, department_arr);
        spindept.setAdapter(arrayAdapter);

        spindept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    departmentname = "ECE";
                }
                if (position == 1) {
                    departmentname = "EEE";
                }
                if (position == 2) {
                    departmentname = "IT";
                }
                if (position == 3) {
                    departmentname = "CSE";
                }
                if (position == 4) {
                    departmentname = "AUTO";
                }
                if (position == 5) {
                    departmentname = "MECH";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                departmentname = "ECE";
            }
        });

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item1, posting_arr);
        spinpost.setAdapter(arrayAdapter1);

        spinpost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    postname = "HOD";
                    ll1.setVisibility(View.GONE);
                    ll2.setVisibility(View.GONE);
                    coyear="0";
                    cosec="0";
                }
                if (position == 1) {
                    postname = "MENDOR";
                    ll1.setVisibility(View.GONE);
                    ll2.setVisibility(View.GONE);
                    coyear="0";
                    cosec="0";
                }
                if (position == 2) {
                    postname = "CO-ORDINATOR";
                    ll1.setVisibility(View.VISIBLE);
                    ll2.setVisibility(View.VISIBLE);
                }
                if (position == 3) {
                    postname = "staff";
                    ll1.setVisibility(View.GONE);
                    ll2.setVisibility(View.GONE);
                    coyear="0";
                    cosec="0";
                }
                if (position == 4) {
                    postname = "none";
                    ll1.setVisibility(View.GONE);
                    ll2.setVisibility(View.GONE);
                    coyear="0";
                    cosec="0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                postname = "HOD";
            }
        });


        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinview2,staffyear);
        staff_post_year.setAdapter(arrayAdapter2);

        staff_post_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0)
                {
                    coyear="I";
                }
                if (position==1)
                {
                    coyear="II";
                }
                if (position==2)
                {
                    coyear="III";
                }
                if (position==3)
                {
                    coyear="IV";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
//                department_1="ECE";
                coyear="I";
            }
        });

        ArrayAdapter<String> arrayAdapter3=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinview3,staffSec);
        staff_post_sec.setAdapter(arrayAdapter3);

        staff_post_sec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0)
                {
                    cosec="A";
                }
                if (position==1)
                {
                    cosec="B";
                }
                if (position==2)
                {
                    cosec="C";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
//                department_1="ECE";
                cosec="A";
            }
        });






        submitbtn.setOnClickListener(new View.OnClickListener() {
            String sname, srollnum, semail, spassword, scnfmpassword, dept, post;
            final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

            @Override
            public void onClick(View v) {
                boolean check;
               /* check = validation();

                if (!check)
                {
                    Toast.makeText(getApplicationContext(),"Fields are empty \n please fill them",Toast.LENGTH_LONG).show();
                }
                if (check)
                {*/
                if (!validatename() | !validateroll() | !validatemail() | !validatepassword()) {
                    return;
                }
                else
                {
//                    String path=getPath(filepath);
                    ApiInteface apiInterface= Api.getClient().create(ApiInteface.class);
                    PojoStaff pojoStaff=new PojoStaff(sname,srollnum,departmentname,spassword,semail,postname,coyear,cosec);
                    Call<PojoStaff> call=apiInterface.insertStaff(pojoStaff);
                    call.enqueue(new Callback<PojoStaff>() {
                        @Override
                        public void onResponse(@NotNull Call<PojoStaff> call, @NotNull Response<PojoStaff> response) {
                            PojoStaff message=response.body();
                            Toast.makeText(getApplicationContext(),"Passed",Toast.LENGTH_LONG).show();
                            if (response.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(),message.getMessage()
                                        +"Registered Successfully",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Rollnumber already exist",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<PojoStaff> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_LONG).show();
                            Log.e("t",t.getLocalizedMessage());
                        }
                    });

                }
            }

           /* private boolean validation() {
                boolean check = false;
                if (!validatename() | !validateroll() | !validatemail() | !validatepassword()) {
                    return;
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Fields are empty \n please fill them",Toast.LENGTH_LONG).show();
                }
                return false;
            }*/

            private boolean validatephoto() {
                boolean check=false;
                if (currentphotopath.length()!=0)
                {
                    check=true;
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Photo is empty !",Toast.LENGTH_LONG).show();
                }
                return check;
            }


            private boolean validatepassword() {
                boolean check = false;
                spassword = password.getEditText().getText().toString();
                scnfmpassword = cnfmpassword.getEditText().getText().toString();
                if (spassword.length() < 4) {
                    password.setError("Invalid format!");
                    return false;
                } else {
                    if (spassword.matches(scnfmpassword)) {
                        password.setError(null);
                        cnfmpassword.setError(null);
                        return true;
                    } else {
                        password.setError("password mismatch");
                        cnfmpassword.setError("password mismatch");
                        return false;
                    }
                }
            }

            private boolean validatemail() {
                boolean check = false;
                semail = email.getEditText().getText().toString();
                if (semail.length() != 0 && semail.matches(emailPattern)) {
                    email.setError(null);
                    return true;
                } else {
                    email.setError("Invalid format!");
                }
                return check;
            }

            private boolean validateroll() {
                boolean check = false;
                srollnum = rollnum.getEditText().getText().toString();
                if (srollnum.length() != 0 && srollnum.length() > 4) {
                    rollnum.setError(null);
                    return true;
                } else if (srollnum.length() >= 0 && srollnum.length() <= 4) {
                    rollnum.setError("Must greater than 4 letters");
                } else {
                    rollnum.setError("Invalid format!");
                }
                return check;
            }

            private boolean validatename() {
                boolean check = false;
                sname = name.getEditText().getText().toString();
                if ((!sname.isEmpty()) && (sname.length() > 4 && sname.length() <= 30)) {
                    name.setError(null);
                    return true;
                } else {
                    name.setError("Invalid format!");
                }
                return check;
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setHelperText("");
                rollnum.getEditText().setText("");
                email.getEditText().setText("");
                password.getEditText().setText("");
                cnfmpassword.getEditText().setText("");
            }
        });

    }

   /* private void askcamerapermission() {
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},STORAGE_PERMISSION_CODE);
        }
        else
        {
//            openCamera();
//            dispatchTakePictureIntent();
            showFileChooser();
        }
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_IMAGE_REQUEST  && resultCode == RESULT_OK && data != null) {
          filepath = data.getData();
          try {
              bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
              staffimg.setImageBitmap(bitmap);
          }
          catch (IOException e)
          {

          }
        }
      *//*  if (requestCode==CAMERA_REQUEST_CODE)
        {
            if (resultCode==Activity.RESULT_OK)
            {
                File file=new File(currentphotopath);
                staffimg.setImageURI(Uri.fromFile(file));
                Toast.makeText(getApplicationContext(),String.valueOf(file),Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"\n"+currentphotopath,Toast.LENGTH_LONG).show();

                Log.e("tag","file="+String.valueOf(file)+"\n"+currentphotopath);

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(file);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);
            }
        }*//*
        super.onActivityResult(requestCode, resultCode, data);
    }

    private File createImageFile() throws IOException
    {
        String timeStamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFilename="JPEG_"+timeStamp+"_";
//        File storageDir=getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File storageDir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File images=File.createTempFile(imageFilename,".jpg",storageDir);
        currentphotopath=images.getAbsolutePath();
        return images;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==STORAGE_PERMISSION_CODE)
        {
            if (grantResults.length<0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
//                openCamera();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Permission is required to use the camera",Toast.LENGTH_LONG).show();
            }
        }
    }

  *//*  private String getPath(Uri uri)
    {
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        cursor.moveToFirst();
        String document_Id=cursor.getString(0);

        document_Id=document_Id.substring(document_Id.lastIndexOf(":")+1);
        cursor.close();

        cursor=getContentResolver().query(
          MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,MediaStore.Images.Media._ID+" = ? ",new String[]{document_Id},null
        );
        cursor.moveToFirst();
        String path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }*//*

    private void requestStoragePermission()
    {
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        else
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
        }
    }
*//*
    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if (requestCode==STORAGE_PERMISSION_CODE)
        {
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {

            }
            else
            {

            }
        }
    }*//*

    private void showFileChooser() {
//        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, CAMERA_REQUEST_CODE);
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }*/

}