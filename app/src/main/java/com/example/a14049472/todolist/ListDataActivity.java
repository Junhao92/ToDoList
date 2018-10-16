package com.example.a14049472.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by User on 26/12/2017.
 */

public class ListDataActivity extends AppCompatActivity {


    DatabaseHelper myDB;
    ListView listView;
    ArrayAdapter<String> listAdapter;
    Button button;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        listView = (ListView) findViewById(R.id.listView);
        button = (Button) findViewById(R.id.add);

        myDB = new DatabaseHelper(this);





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListDataActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });




        //populate an ArrayList<String> from the databases and then view it
        final ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getListContent();



        if (data.getCount() == 0) {
            Toast.makeText(ListDataActivity.this, "The database was empty", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                // get the value from the database in column 1
                // then add it to the ArrayList
                theList.add(data.getString(1));
                // create the list adapter and set the adapter
                listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);


            }
        }

        // delete from database , have to remove from that position and notify the adapter
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    myDB.deleteName(theList.get(i)); // theList is the listName
                    theList.remove(i);
                    listAdapter.notifyDataSetChanged(); //change to your adapter instance here
                    Toast.makeText(ListDataActivity.this, "item removed", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

        }
        }






