package com.example.oving2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity {
    private Intent intent;


    //TODO
    //Add more safety regarding input from user (try catch)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        intent = new Intent("maxschau.oppgave1");

    }

    public void handleAdd(View v) {
        handleClick(true);
    }

    public void handleMultiply(View v) {
        handleClick(false);
    }


    public void handleClick(boolean add) {
        TextView number1TextView = (TextView) findViewById(R.id.number1);
        TextView number2TextView = (TextView) findViewById(R.id.number2);
        EditText answerEditText = (EditText) findViewById(R.id.editTextAnswer);
        EditText upperLimitEditText = (EditText) findViewById(R.id.upperlimitEditText);

        int number1 = Integer.parseInt(number1TextView.getText().toString());
        int number2 = Integer.parseInt(number2TextView.getText().toString());
        int answer = -1;
        if (add) {
            answer = number1 + number2;
        } else {
            answer = number1 * number2;
        }
        int answerFromUser = Integer.parseInt(answerEditText.getText().toString());
        int upperLimit = Integer.parseInt(upperLimitEditText.getText().toString());
        intent.putExtra("limit", upperLimit);

        Context context = getApplicationContext();

        int duration = Toast.LENGTH_SHORT;
        CharSequence text;
        Toast toast;

        if (answer == answerFromUser) {
            text = getString(R.string.correct);
        } else {
            text = getString(R.string.wrong) + answer;
        }
        toast = Toast.makeText(context, text, duration);
        toast.show();

        //Get two new generated numbers
        startActivityForResult(intent, 1);
        startActivityForResult(intent, 2);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView number1TextView = (TextView) findViewById(R.id.number1);
        TextView number2TextView = (TextView) findViewById(R.id.number2);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                number1TextView.setText(Integer.toString(data.getIntExtra("RandomNumber", -1)));
            }
            if (requestCode == 2) {
                number2TextView.setText(Integer.toString(data.getIntExtra("RandomNumber", -1)));
            }
        }
    }
}
