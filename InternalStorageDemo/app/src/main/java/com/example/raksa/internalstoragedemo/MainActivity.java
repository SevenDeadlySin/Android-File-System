package com.example.raksa.internalstoragedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    //View
    EditText fileName;
    EditText message;
    Button buttonSave;
    Button buttonDisplay;
    Button buttonStoragePath;
    TextView textViewStoragePath;
    Button buttonFileList;
    TextView textViewFileList;
    Button buttonDelete;
    EditText editTextDelete;


    FileOutputStream fileOutputStream = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Interaction with View
        fileName = (EditText) findViewById(R.id.editTextFileName);
        message = (EditText) findViewById(R.id.editTextMessage);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonDisplay = (Button) findViewById(R.id.buttonDisplay);
        buttonStoragePath = (Button) findViewById(R.id.buttonStoragePath);
        textViewStoragePath = (TextView) findViewById(R.id.textViewStoragePath);
        buttonFileList = (Button) findViewById(R.id.buttonShowListOfAppPrivateFiles);
        textViewFileList = (TextView) findViewById(R.id.textViewPrivateFileList);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        editTextDelete = (EditText) findViewById(R.id.editTextDelete);

        //region buttonSave
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    fileOutputStream = openFileOutput(fileName.getText().toString().toLowerCase()+".txt",MODE_PRIVATE);
                    fileOutputStream.write(message.getText().toString().getBytes());
                    Toast.makeText(getApplicationContext(),"Successfully Saved!",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"File Already Exist!!",Toast.LENGTH_SHORT).show();
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
        });
        //endregion

        //region buttonDisplay
        buttonDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),DisplayActivity.class);
                startActivity(intent);
            }
        });
        //endregion

        //region buttonStoragePath
        buttonStoragePath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewStoragePath.setText(getFilesDir().toString());
            }
        });
        //endregion

        //region buttonFileList
        buttonFileList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] listOfFile = fileList();
                StringBuilder stringOfFileList = new StringBuilder();
                if (listOfFile.length!=0){
                    for (String string : listOfFile){
                        stringOfFileList.append("["+string+"] ");
                    }
                    textViewFileList.setText(stringOfFileList);
                }
                else {
                    textViewFileList.setText("No File Was Found!");
                }
            }
        });
        //endregion

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isDeleted = deleteFile(editTextDelete.getText().toString().toLowerCase()+".txt");
                if (isDeleted){
                    String[] listOfFile = fileList();
                    StringBuilder stringOfFileList = new StringBuilder();
                    if (listOfFile.length!=0){
                        for (String string : listOfFile){
                            stringOfFileList.append("["+string+"] ");
                        }
                        textViewFileList.setText(stringOfFileList);
                    }
                    else {
                        textViewFileList.setText("No File Was Found!");
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"No File was Found",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
