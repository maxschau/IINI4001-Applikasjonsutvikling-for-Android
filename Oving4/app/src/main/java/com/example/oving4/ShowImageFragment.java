package com.example.oving4;

import android.os.Bundle;
import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ShowImageFragment extends Fragment {
    private TextView textDescription;
    private ImageView image;

    public ShowImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_image, container, false);
        textDescription = (TextView) view.findViewById(R.id.textViewDescription);
        image = (ImageView) view.findViewById(R.id.imageView);
        return view;
    }
    public void changeImageShown(int idOfImage, String descriptionOfImage) {
        image.setImageResource(idOfImage);
        textDescription.setText(descriptionOfImage);
    }
}