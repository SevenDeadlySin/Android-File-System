package com.example.raksa.externalstorage;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    //region View
    EditText editTextExternalPrivateData , editTextExternalPublicData;
    Button buttonSaveExternalPrivate ,buttonSaveExternalPublic , buttonReadExternalPrivate
            ,buttonReadExternalPublic;
    TextView textViewExternalPrivateReadData , textViewExternalPublicReadData;
    //endregion

    //File Stream
    FileOutputStream fileOutputStream = null;
    FileInputStream fileInputStream = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       int isPermissionGranted = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
       if (isPermissionGranted!=PackageManager.PERMISSION_GRANTED){
           ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},123);
       }
       if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)
               != PackageManager.PERMISSION_GRANTED){
           ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},458);
       }

        //region get view references
        editTextExternalPrivateData = findViewById(R.id.editTextExternalPrivateData);
        editTextExternalPublicData = findViewById(R.id.editTextExternalPublicData);
        buttonSaveExternalPrivate = findViewById(R.id.buttonSaveExternalPrivate);
        buttonSaveExternalPublic = findViewById(R.id.buttonSaveExternalPublic);
        buttonReadExternalPrivate = findViewById(R.id.buttonReadExternalPrivate);
        buttonReadExternalPublic = findViewById(R.id.buttonReadExternalPublic);
        textViewExternalPrivateReadData = findViewById(R.id.textViewExternalPrivateReadData);
        textViewExternalPublicReadData = findViewById(R.id.textViewExternalPubliReadData);
        //endregion

        //region If User Click On Button Save As External Private
        buttonSaveExternalPrivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataAsExternalPrivateFile();
            }
        });
        //endregion

        //region If User Click On Button Read External Private
        buttonReadExternalPrivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readDataFromExternalPrivateFile();
            }
        });
        //endregion

        //region If User Click On Button Save As External Public
        buttonSaveExternalPublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataAsExternalPublicFile();
            }
        });
        //endregion

        //region If User Click On Button Read External Public
        buttonReadExternalPublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readDataFromExternalPublicFile();
            }
        });
        //endregion
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //My Method
    private void saveDataAsExternalPrivateFile(){

        try {
            File fileDir = new File(getExternalFilesDir("TextFolder"),"myexternalprivatefile.txt");
            fileOutputStream = new FileOutputStream(fileDir);
            fileOutputStream.write(editTextExternalPrivateData.getText().toString().getBytes());
            Toast.makeText(getApplicationContext(),"Successfully Save The File!",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Fail To Save The File!",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Fail To Save The File!",Toast.LENGTH_SHORT).show();
        }finally {
            if (fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void readDataFromExternalPrivateFile(){
        try {
            fileInputStream = new FileInputStream(new File(getExternalFilesDir("TextFolder"),"myexternalprivatefile.txt"));

            int readData;
            StringBuffer data = new StringBuffer("Read Data : ");

            while ((readData = fileInputStream.read())!=-1){
                data.append((char)readData);
            }

            textViewExternalPrivateReadData.setText(data);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveDataAsExternalPublicFile(){

        File fileDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(fileDir,"mypublicexternalfile.txt");
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(editTextExternalPublicData.getText().toString().getBytes());
            Toast.makeText(getApplicationContext(),"Successfully Save The File!",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Fail To Save The File!",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Fail To Save The File!",Toast.LENGTH_SHORT).show();
        }finally {
            if (fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void readDataFromExternalPublicFile(){

        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS),"mypublicexternalfile.txt");
        try {
            fileInputStream = new FileInputStream(file);

            int readData;
            StringBuffer data = new StringBuffer("Read Data : ");

            while ((readData = fileInputStream.read())!= -1){
                data.append((char) readData);
            }

            textViewExternalPublicReadData.setText(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
