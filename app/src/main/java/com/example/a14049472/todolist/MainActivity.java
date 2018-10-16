package com.example.a14049472.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    DatabaseHelper myDB;
    Button btnAdd;
    Button btnList;
    TextView tvView;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnList = (Button) findViewById(R.id.btnList);
        tvView = (TextView) findViewById(R.id.Textview);
        editText = (EditText) findViewById(R.id.editText);
        myDB = new DatabaseHelper(this);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newEntry = editText.getText().toString();
                if (newEntry.length() != 0) {
                    AddData(newEntry);
                    editText.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "You must put something in the text field", Toast.LENGTH_LONG).show();
                }
            }

        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



    public void AddData(String newEntry) {
        boolean insertData = myDB.addData(newEntry);
        // check inserted successfully
        if (insertData == true) {
            Toast.makeText(MainActivity.this, "Successfully Entered Data!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }



}

