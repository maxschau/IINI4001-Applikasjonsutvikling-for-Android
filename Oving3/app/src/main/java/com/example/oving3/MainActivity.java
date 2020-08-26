package com.example.oving3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> listOfNames = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private TextView inputName;
    private TextView inputBirth;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputName = findViewById(R.id.editTextName);
        inputBirth = findViewById(R.id.editTextTextPersonName7);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfNames);
        listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                LayoutInflater linf = LayoutInflater.from(getApplicationContext());
                final View inflator = linf.inflate(R.layout.changeperson, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setTitle(R.string.change);

                AlertDialog dialog = alertDialogBuilder.create();
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText inputName = inflator.findViewById(R.id.modalName);
                        EditText inputBirth = inflator.findViewById(R.id.modalBirth);
                        if(!inputName.getText().toString().equals("") && !inputBirth.getText().toString().equals("")) {
                            listOfNames.set(position, inputName.getText().toString() + ", " + inputBirth.getText().toString());
                            adapter.notifyDataSetChanged();
                        } else {
                            notifyFailure();
                        }

                    }
                });


                dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.setView(inflator);
                dialog.show();
            }});
    }

    public void addItems(View v) {
        String name = inputName.getText().toString();
        String birth = inputBirth.getText().toString();
        if (checkInput()) {
            adapter.add(name + ", " + birth);
            clearFields();
        } else {
            notifyFailure();
        }
    }

    public void notifyFailure() {
        Context context = getApplicationContext();
        CharSequence text = "You must fill both fields";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }





    public void clearFields() {
        inputName.setText("");
        inputBirth.setText("");
    }

    public boolean checkInput() {
        return !inputName.getText().toString().equals("") && !inputBirth.getText().toString().equals("");
    }
}