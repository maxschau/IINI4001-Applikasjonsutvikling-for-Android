package com.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Client extends Thread {
    private final static String TAG = "Client";
    private final static String IP = "10.0.2.2";
    private final static int PORT = 12345;
    private MainActivity mainActivity;

    public Client(MainActivity activity) {
        mainActivity = activity;
    }

    public void run() {
        Socket s 			= null;
        PrintWriter out		= null;
        BufferedReader in 	= null;

        try {
            s = new Socket(IP, PORT);
            Log.v(TAG, "C: Connected to server" + s.toString());
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String res = in.readLine();
            Log.i(TAG,res);

            EditText number1 = (EditText) mainActivity.findViewById(R.id.editTextTextNumber1);
            int n1 = Integer.parseInt(number1.getText().toString());
            EditText number2 = (EditText) mainActivity.findViewById(R.id.editTextTextNumber2);
            int n2 = Integer.parseInt(number2.getText().toString());
            out.println(n1 + " " + n2);

            res = in.readLine();
            TextView answer = (TextView) mainActivity.findViewById(R.id.textViewAnswer);
            //answer.setVisibility(View.VISIBLE);
            //answer.setText(res);
            Log.d("ANSWER", "ANSWER: " + res);
            mainActivity.setText(answer, res);


        } catch (IOException e) {
            e.printStackTrace();
        }finally{//close socket!!
            try{
                out.close();
                in.close();
                s.close();
            }catch(IOException e){}
        }
    }
}