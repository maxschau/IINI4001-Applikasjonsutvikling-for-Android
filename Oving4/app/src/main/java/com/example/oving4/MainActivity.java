package com.example.oving4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements ImageListFragment.OnFragmentInteractionListener{
    private ShowImageFragment showImageFragment;
    private String[] imageList;
    private String[] description;
    private int selectedImage = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showImageFragment = (ShowImageFragment) getFragmentManager().findFragmentById(R.id.fragmentShowImage);
        imageList = getResources().getStringArray(R.array.imageList);
        description = getResources().getStringArray(R.array.description);


    }

    public int getResourceId(int id) {
        int resourceID = -1;
        switch (id) {
            case 0:
                resourceID = getResources().getIdentifier("vif", "drawable", this.getPackageName());
                break;
            case 1:
                resourceID = getResources().getIdentifier("rbk", "drawable", this.getPackageName());
                break;
            case 2:
                resourceID = getResources().getIdentifier("brann", "drawable", this.getPackageName());
                break;
        }
        return resourceID;
    }

    @Override
    public void onFragmentInteraction(int id , String description) {
        selectedImage = id;
        int resourceID = getResourceId(id);
        showImageFragment.changeImageShown(resourceID,description);

    }

    public void nextImage(View view) {
        if (selectedImage >= 2) {
            selectedImage = 0;
        } else {
            selectedImage++;
        }
        showImageFragment.changeImageShown(getResourceId(selectedImage), description[selectedImage]);
    }

    public void lastImage(View view) {
        if (selectedImage == 0) {
            selectedImage = 2;
        } else {
            selectedImage--;
        }
        showImageFragment.changeImageShown(getResourceId(selectedImage), description[selectedImage]);
    }
}