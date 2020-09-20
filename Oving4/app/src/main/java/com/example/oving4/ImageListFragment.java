package com.example.oving4;

import android.app.Activity;
import android.os.Bundle;

import android.app.ListFragment;


import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ImageListFragment extends ListFragment {
    private OnFragmentInteractionListener mListener;
    private String[] imageList;
    private String[] description;

    public ImageListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageList = getResources().getStringArray(R.array.imageList);
        description = getResources().getStringArray(R.array.description);
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, imageList));

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity); try {
            mListener = (OnFragmentInteractionListener) activity; } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mListener.onFragmentInteraction(position, description[position]);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(int id, String description);
    }
}