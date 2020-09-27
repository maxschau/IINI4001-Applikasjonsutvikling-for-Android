package com.example.client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void setText(final TextView text, final String value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });
    }

    public void connectAndSendToServer(View v) {
        EditText number1 = (EditText) findViewById(R.id.editTextTextNumber1);
        int n1 = Integer.parseInt(number1.getText().toString());
        EditText number2 = (EditText) findViewById(R.id.editTextTextNumber2);
        int n2 = Integer.parseInt(number2.getText().toString());
        Client client = new Client(this);
        client.start();



    }


}