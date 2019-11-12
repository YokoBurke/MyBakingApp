package com.example.android.mybakingapp;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
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
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;


public class MovieFragment extends Fragment implements ExoPlayer.EventListener {


    final static String ClASS_NAME = MovieFragment.class.getSimpleName();
    private String thisVideoURL;
    TextView videoUrlText;
    private int mListIndex;
    private SimpleExoPlayerView simpleExoPlayerView;
    private SimpleExoPlayer mExoPlayer;
    private ImageView noVideoImage;
    String myVideoUrl;
    View view;

    private MediaSessionCompat mMediaSession;
    private PlaybackStateCompat.Builder mStateBuilder;
    private NotificationManager mNotificationManager;


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

            initializePlayer(Uri.parse(myVideoUrl));

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

        //mExoPlayer.addListener((ExoPlayer.EventListener) view.getContext());
        mExoPlayer.addListener(this);

    }


    public void setMovieUrl(String newUrl){
        myVideoUrl = newUrl;
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState){
        Log.i(ClASS_NAME, "OnPlayerStateChange Launced" + mExoPlayer.getPlaybackState());
        if ((playbackState == ExoPlayer.STATE_READY) && playWhenReady){
            Log.d(ClASS_NAME, "OnPlayerStateChanged: PLAYING");
            mStateBuilder.setState(PlaybackStateCompat.STATE_PLAYING, mExoPlayer.getCurrentPosition(), 1f);
        } else if ((playbackState == ExoPlayer.STATE_READY)){
            Log.d(ClASS_NAME, "onPlayerStateChanged: PAUSED");
            mStateBuilder.setState(PlaybackStateCompat.STATE_PAUSED, mExoPlayer.getCurrentPosition(), 1f);
        }

        //mMediaSession.setPlaybackState(mStateBuilder.build());
    }

    private void initializeMediaSession(){
        mMediaSession = new MediaSessionCompat(view.getContext(), ClASS_NAME);
        mMediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

                mMediaSession.setMediaButtonReceiver(null);

                mStateBuilder = new PlaybackStateCompat.Builder()
                        .setActions(
                                PlaybackStateCompat.ACTION_PLAY |
                                        PlaybackStateCompat.ACTION_PAUSE |
                                        PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                                        PlaybackStateCompat.ACTION_PLAY_PAUSE);

        mMediaSession.setPlaybackState(mStateBuilder.build());

        mMediaSession.setCallback(new MySessionCallback());

        mMediaSession.setActive(true);
    }

    private class MySessionCallback extends MediaSessionCompat.Callback {
        @Override
        public void onPlay() {
            mExoPlayer.setPlayWhenReady(true);
        }

        @Override
        public void onPause() {
            mExoPlayer.setPlayWhenReady(false);
        }

        @Override
        public void onSkipToPrevious() {
            mExoPlayer.seekTo(0);
        }
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }

    private void releasePlayer(){
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    @Override
    public void onPause(){
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
        mMediaSession.setActive(false);
    }

}