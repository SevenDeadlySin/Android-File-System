package com.example.raksa.cachestorage;

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

    //View
    Button buttonSaveToInternalCache , buttonSaveToExternalCache ,
            buttonReadFromInternalCache , buttonReadFromExternalCache;
    TextView textViewInternalCacheData , textViewExternalCacheData;
    EditText editTextInternalCacheData , editTextExternalCacheData;


    FileOutputStream fileOutputStream = null;
    FileInputStream fileInputStream = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region get view reference
        buttonSaveToInternalCache = findViewById(R.id.buttonInternalSave);
        buttonSaveToExternalCache = findViewById(R.id.buttonExternalSave);
        buttonReadFromInternalCache = findViewById(R.id.buttonInternalRead);
        buttonReadFromExternalCache = findViewById(R.id.buttonExternalRead);
        textViewInternalCacheData = findViewById(R.id.textViewInternalData);
        textViewExternalCacheData = findViewById(R.id.textViewExternalData);
        editTextInternalCacheData = findViewById(R.id.editTextInternalCacheData);
        editTextExternalCacheData = findViewById(R.id.editTextExternalCacheData);
        //endregion

        //region If Click Save Internal Button
        buttonSaveToInternalCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToInternalCache();
            }
        });
        //endregion

        //region If Click Read Internal Button
        buttonReadFromInternalCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFromInternalCache();
            }
        });
        //endregion

        //region If Click Save External Button
        buttonSaveToExternalCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToExternalCache();
            }
        });
        //endregion

        //region If Click Read External Button
        buttonReadFromExternalCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFromExternalCache();
            }
        });
        //endregion
    }

    private void saveToInternalCache(){

        File internalCacheFileDir = new File(getCacheDir(),"myinternalcache.txt");
        try {
            fileOutputStream = new FileOutputStream(internalCacheFileDir) ;
            fileOutputStream.write(editTextInternalCacheData.getText().toString().getBytes());
            Toast.makeText(getApplicationContext(),"Successfully Saved To Cache!",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Fail To Saved To Cache!",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Fail To Saved To Cache!",Toast.LENGTH_SHORT).show();
        }
        finally {
            if (fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void readFromInternalCache(){
        File internalCacheFileDir = new File(getCacheDir(),"myinternalcache.txt");
        try {
            fileInputStream =new FileInputStream(internalCacheFileDir);
            int readData;
            StringBuffer data = new StringBuffer();

            while ((readData = fileInputStream.read())!=-1) {
                data.append((char) readData);
            }

            textViewInternalCacheData.setText(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Cache File not Found",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveToExternalCache(){

        File externalCacheFileDir = new File(getExternalCacheDir(),"myexternalcache.txt");
        try {
            fileOutputStream = new FileOutputStream(externalCacheFileDir);
            fileOutputStream.write(editTextExternalCacheData.getText().toString().getBytes());
            Toast.makeText(getApplicationContext(),"Successfully Saved The Cache!",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Fail to Saved The Cache!",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Fail to Saved The Cache!",Toast.LENGTH_SHORT).show();
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

    private void readFromExternalCache(){
        File externalCacheFileDir = new File(getExternalCacheDir(),"myexternalcache.txt");
        try {
            fileInputStream =new FileInputStream(externalCacheFileDir);
            int readData;
            StringBuffer data = new StringBuffer();

            while ((readData = fileInputStream.read())!=-1) {
                data.append((char) readData);
            }

            textViewExternalCacheData.setText(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Cache File not Found",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
