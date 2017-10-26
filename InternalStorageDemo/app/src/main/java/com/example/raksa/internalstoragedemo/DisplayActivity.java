package com.example.raksa.internalstoragedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DisplayActivity extends AppCompatActivity {

    //Views
    Button buttonDisplayData;
    EditText editTextDisplayFileName;
    TextView textViewDisplaData;

    FileInputStream fileInputStream = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        getSupportActionBar().setTitle("Display Internal Data");

        //interrection with View
        buttonDisplayData = (Button) findViewById(R.id.buttonDisplayData);
        editTextDisplayFileName = (EditText) findViewById(R.id.editTextDisplayFileName);
        textViewDisplaData = (TextView) findViewById(R.id.textViewDisplayData);


        buttonDisplayData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int displayData;
                StringBuffer stringBuffer = new StringBuffer();

                try {
                    fileInputStream = openFileInput(editTextDisplayFileName.getText().toString().toLowerCase()+".txt");
                    while ((displayData = fileInputStream.read()) != -1){
                        stringBuffer.append((char)displayData);
                    }
                    textViewDisplaData.setText(stringBuffer);
                    Toast.makeText(getApplicationContext(),"Successfully Read The Data!",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Failed To Read The Data!",Toast.LENGTH_SHORT).show();
                }
                finally {
                    if (fileInputStream!=null){
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });

    }
}
