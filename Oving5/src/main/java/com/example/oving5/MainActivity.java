package com.example.oving5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private HttpConnection httpConnection;
    private TextView labelName;
    private TextView labelCardNumber;
    private EditText editName;
    private EditText editCardNumber;
    private TextView labelDescription;
    private EditText editGuess;
    private Button sendButton;
    private Button restartButton;
    private boolean game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        httpConnection = new HttpConnection(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = false;
        labelName = findViewById(R.id.textView2);
        labelCardNumber = findViewById(R.id.textView3);
        editName = findViewById(R.id.editTextName);
        editCardNumber = findViewById(R.id.editTextCard);
        labelDescription = findViewById(R.id.labelDescription);
        editGuess = findViewById(R.id.editTextGuess);
        sendButton = findViewById(R.id.button);
        restartButton = findViewById(R.id.button2);



        displayFirstSection(true);
        displaySecondSection(false);
    }

    public void onSend(View v) {
        Map<String, String> parameters = new HashMap<>();
        if (game) {
            parameters.put("tall", editGuess.getText().toString());
            httpConnection.startNewThread(parameters);
        } else {

            EditText name = (EditText) findViewById(R.id.editTextName);
            EditText cardNumber = (EditText) findViewById(R.id.editTextCard);
            parameters.put("navn", name.getText().toString());
            parameters.put("kortnummer", cardNumber.getText().toString());
            httpConnection.startNewThread(parameters);
            displayFirstSection(true);
            displaySecondSection(false);
            editGuess.setText("");
            game = true;
        }

    }

    private void clearInputField() {
        editName.setText("");
        editCardNumber.setText("");
    }

    public void onRestart(View v) {
        game = false;
        displayFirstSection(true);
        displaySecondSection(false);
        clearInputField();
        sendButton.setClickable(true);

    }

    //TODO:
    //Give the private helping methods better name

    private void displayFirstSection(boolean display) {
        labelName.setVisibility(display ? View.VISIBLE : View.GONE) ;
        labelCardNumber.setVisibility(display ? View.VISIBLE : View.GONE);
        editName.setVisibility(display ? View.VISIBLE : View.GONE);
        editCardNumber.setVisibility(display ? View.VISIBLE : View.GONE);
    }

    private void displaySecondSection(boolean display) {
        labelDescription.setVisibility(display ? View.VISIBLE : View.GONE);
        editGuess.setVisibility(display ? View.VISIBLE : View.GONE);
    }

    public void changeDisplay(String response) {
        if (response.contains("Oppgi et tall mellom ")) {
            displayFirstSection(false);
            displaySecondSection(true);
            sendButton.setClickable(true);
            labelDescription.setText(response);


        } else if (response.contains("du må starte på nytt")) {
            displayFirstSection(false);
            displaySecondSection(false);
            labelDescription.setText(response);
            sendButton.setClickable(false);
            labelDescription.setVisibility(View.VISIBLE);


        } else if (response.contains("du har vunnet")) {
            displayFirstSection(false);
            labelDescription.setText(response);
            sendButton.setClickable(false);


        } else {
            //New try
            displayFirstSection(false);
            labelDescription.setText(response);

        }
    }

}