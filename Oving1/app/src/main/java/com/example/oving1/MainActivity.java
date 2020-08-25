package com.example.oving1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        menu.add("Max");
        menu.add("Torre Schau");
        Log.i("onCreateOptionsMenu()","Menu created");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getTitle().equals("Max")){
            Log.i("onOptionsItemSelected()","Option 'Max' is selected by the user");
        }
        if (item.getTitle().equals("Torre Schau")){
            Log.i("onOptionsItemSelected()","Option 'Torre Schau' is selected by the user");
        }
        return true;
    }


        @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("DUST", "DUSTER");
    }
}