package com.example.oving7;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;


public class MainActivity extends AppCompatActivity {
    private ArrayList<Pair<String, String>> dataFromFile;
    private boolean showAuthors = false;
    private Button changeBtn;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeBtn = findViewById(R.id.changeBtn);
        dataFromFile = new ArrayList<>();

        try {
            databaseManager = new DatabaseManager(this.getApplicationContext());
            databaseManager.clean();
            if (readData()) {
                for (Pair<String, String> pair : dataFromFile) {
                    databaseManager.insert(pair.first, pair.second);
                }
            }

            showResults(databaseManager.getAllBooks());

        } catch (Exception e) {
            e.printStackTrace();
        }
        TextView t = (TextView) findViewById(R.id.tw1);
        String bgcolor = getDefaultSharedPreferences(this).getString("bgColor", "#ffffff");
        t.setBackgroundColor(Color.parseColor(bgcolor));

    }

    public void changeView(View v) {
        Log.i("PUSHED", "PUTTON PUSHED");
        if (showAuthors) {
            showAuthors = false;
            changeBtn.setText(R.string.authors);
            showResults(databaseManager.getAllBooks());

        } else {
            showAuthors = true;
            changeBtn.setText(R.string.books);
            showResults(databaseManager.getAllAuthors());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        TextView t = (TextView)findViewById(R.id.tw1);
        SharedPreferences appPrefs = getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefsEditor = appPrefs.edit();

        switch (item.getItemId()) {
            case R.id.lightblue:
                prefsEditor.putString("bgColor","#00bfff");
                prefsEditor.commit();
                t.setBackgroundColor(Color.parseColor(getDefaultSharedPreferences(this).getString("bgColor", "#ffffff")));
                return (true);
            case R.id.yellow:
                prefsEditor.putString("bgColor","#f0f209");
                prefsEditor.commit();
                t.setBackgroundColor(Color.parseColor(getDefaultSharedPreferences(this).getString("bgColor", "#ffffff")));
                return (true);
            case R.id.white:
                prefsEditor.putString("bgColor","#ffffff");
                prefsEditor.commit();
                t.setBackgroundColor(Color.parseColor(getDefaultSharedPreferences(this).getString("bgColor", "#ffffff")));
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }


    public boolean readData() {
        InputStream inputStream = getResources().openRawResource(R.raw.books);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        String line = "";

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(",");
                dataFromFile.add(new Pair<String, String>(words[0], words[1]));
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showResults(ArrayList<String> list) {
        StringBuffer res = new StringBuffer("");
        for (String s : list) {
            res.append(s + "\n");
        }
        TextView t = (TextView) findViewById(R.id.tw1);

        t.setText(res);
    }
}