package com.example.oving2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private int randomValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int upperLimit = 100;
        Intent intent = new Intent("maxschau.oppgave1");
        intent.putExtra("limit", upperLimit);
        startActivityForResult(intent, 1);

        startActivity(new Intent("maxschau.calculator"));



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            TextView view = (TextView) findViewById(R.id.text_view_random_number);
            randomValue = data.getIntExtra("RandomNumber", -1);
            Log.d("RandomValue", "value: " + randomValue);
            view.setText("Random verdi: " + randomValue);
        }
    }




}