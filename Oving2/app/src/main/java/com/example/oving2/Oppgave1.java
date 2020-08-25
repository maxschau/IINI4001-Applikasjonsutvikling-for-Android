package com.example.oving2;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

public class Oppgave1 extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oppgave1);
        //Context context = getApplicationContext();
        int numberGenerated = getRandomNumber();
        //CharSequence text = Integer.toString(numberGenerated);
        //int duration = Toast.LENGTH_SHORT;



        //Oppgave A
        //Toast toast = Toast.makeText(context, text, duration);
        //toast.show();

        //Oppgave C
        Intent intent = new Intent();
        intent.putExtra("RandomNumber", numberGenerated);
        setResult(RESULT_OK,intent);
        finish();
    }

    public int getRandomNumber() {
        Random random = new Random();
        //Oppgave B
        int upperLimit = getIntent().getIntExtra("limit", 100);
        int numberGenerated = random.nextInt(upperLimit) + 1;

        return numberGenerated;
    }



}
