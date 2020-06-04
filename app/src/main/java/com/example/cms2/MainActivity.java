package com.example.cms2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //variable component to be used in this class
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting layout resource file for this class
        setContentView(R.layout.activity_main);

        //locating listView in layout resource file
        listView = findViewById(R.id.listView);
        //setting onclick listener to listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (id == 0){
                    //if the first element in the listView is selected start new activity called CMSPortal
                    Intent intent = new Intent(MainActivity.this, CMSPortal.class);
                    //start the above intent
                    startActivity(intent);
                }
            }
        });
    }
}
