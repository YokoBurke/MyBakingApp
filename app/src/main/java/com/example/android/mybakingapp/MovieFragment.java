package com.example.android.mybakingapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.android.mybakingapp.data.Steps;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;


public class MovieFragment extends Fragment {


    final static String ClASS_NAME = MovieFragment.class.getSimpleName();
    private String thisVideoURL;
    TextView videoUrlText;
    private int mListIndex;
    private SimpleExoPlayerView simpleExoPlayerView;
    private SimpleExoPlayer mExoPlayer;
    private ImageView noVideoImage;
    String myVideoUrl;
    View view;


    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Log.i(ClASS_NAME, "OnCreateView is Called 2");


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
        view =inflater.inflate(R.layout.fragment_movie, container, false);


        simpleExoPlayerView = view.findViewById(R.id.playerView);
        noVideoImage = view.findViewById(R.id.no_video);

        videoUrlText  = (TextView) view.findViewById(R.id.my_video);
        videoUrlText.setText(myVideoUrl);

        displayMovieData();

        /* EachStepActivity eachStepActivity = (EachStepActivity) getActivity();
        myVideoUrl = eachStepActivity.getMyData();

         */


        /* if (getArguments() != null) {
            thisVideoURL = getArguments().getString("stepurl");
            Log.i(ClASS_NAME, thisVideoURL);
            videoURL.setText(thisVideoURL);
        } else {
            videoURL.setText("Is this working??");
        } */

        return view;
    }

    private void displayMovieData(){
        if (myVideoUrl == null){
            simpleExoPlayerView.setVisibility(View.GONE);
            noVideoImage.setVisibility(View.VISIBLE);

        } else {

            simpleExoPlayerView.setVisibility(View.VISIBLE);
            noVideoImage.setVisibility(View.GONE);

        }
    }

    private void initializePlayer(Uri mediaUri){
        if (mExoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(view.getContext(), trackSelector, loadControl);
            simpleExoPlayerView.setPlayer(mExoPlayer);
        }

        MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(view.getContext(), Util.getUserAgent(view.getContext(),
                "Baking App")), new DefaultExtractorsFactory(), null, null);
        mExoPlayer.prepare(mediaSource);
        mExoPlayer.setPlayWhenReady(true);

    }


    public void setMovieUrl(String newUrl){
        myVideoUrl = newUrl;
    }

    @Override
   public void onDestroy() {
        super.onDestroy();
        mExoPlayer = null;
    }

}