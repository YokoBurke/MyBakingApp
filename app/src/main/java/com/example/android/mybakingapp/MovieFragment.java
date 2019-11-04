package com.example.android.mybakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.android.mybakingapp.data.Steps;

import java.util.ArrayList;


public class MovieFragment extends Fragment {


    final static String ClASS_NAME = MovieFragment.class.getSimpleName();
    private String thisVideoURL;
    TextView videoURL;

    OnStepButtonClickListener mCallback;

    public void setOnStepButtonListener(OnStepButtonClickListener callback){
        mCallback = callback;
    }

    public interface OnStepButtonClickListener{
        void onStepButtonSelected(String myNewMovieUrl);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        try {
            mCallback = (OnStepButtonClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnStepButtonClickListener");
        }
    }

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        /* Intent movieChildIntent = getActivity().getIntent();
        if (movieChildIntent.hasExtra(Intent.EXTRA_TEXT)) {

            mySteps = (Steps) movieChildIntent.getParcelableExtra(Intent.EXTRA_TEXT);
            myRecipeName = movieChildIntent.getStringExtra("recipe_name");
            currentStep = mySteps.getId();
            Log.i(ClASS_NAME, "Video URL: " + mySteps.getVideoURL());


            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(myRecipeName);


        } */
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_movie, container, false);

        videoURL  = (TextView) view.findViewById(R.id.my_video);
        EachStepActivity eachStepActivity = (EachStepActivity) getActivity();
        String myDataFromActivity = eachStepActivity.getMyData();
        videoURL.setText(myDataFromActivity);

        /* if (getArguments() != null) {
            thisVideoURL = getArguments().getString("stepurl");
            Log.i(ClASS_NAME, thisVideoURL);
            videoURL.setText(thisVideoURL);
        } else {
            videoURL.setText("Is this working??");
        } */






        return view;
    }


}