package com.example.android.mybakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.android.mybakingapp.data.BakingRecipe;
import com.example.android.mybakingapp.data.Ingredients;
import com.example.android.mybakingapp.data.Steps;

import java.util.ArrayList;

public class EachStepActivity extends AppCompatActivity {

    final static String ClASS_NAME = EachStepActivity.class.getSimpleName();


    Steps mySteps;
    private ArrayList<Steps> myStepsData;
    String myRecipeName;
    int currentStep;

    private String myStepVideoUrl;
    private String myStepDescription;

    Button nextButton;
    Button previousButton;

    MovieFragment movieFragment;
    TextView stepDescriptionTextView;


    private boolean isTablet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_step);

        stepDescriptionTextView = (TextView) findViewById(R.id.step_descriptions);

        Intent childIntent = this.getIntent();
        if (childIntent.hasExtra(Intent.EXTRA_TEXT)) {

            myRecipeName = childIntent.getStringExtra("recipe_name");
            mySteps = (Steps) childIntent.getParcelableExtra(Intent.EXTRA_TEXT);
            currentStep = mySteps.getId();
            myStepsData = childIntent.getParcelableArrayListExtra("vlist");

            myStepVideoUrl = mySteps.getVideoURL();
            Log.i(ClASS_NAME, "Video URL: " + myStepVideoUrl + "  " + Integer.toString(currentStep) +" " + Integer.toString(myStepsData.size()));
            getSupportActionBar().setTitle(myRecipeName + "Successful");

        }

        stepDescriptionTextView.setText(mySteps.getDescription());
        Bundle urlBundle = new Bundle();
        urlBundle.putString("myUrl", myStepVideoUrl);

        movieFragment = new MovieFragment();
        movieFragment.setArguments(urlBundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.video_container, movieFragment)
                .commit();

        nextButton = (Button) findViewById(R.id.steps_next);
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Do Something
                if (currentStep >= myStepsData.size() -1){
                    Toast.makeText(getApplicationContext(), "This is the last step.", Toast.LENGTH_LONG).show();
                } else {
                    currentStep++;

                    updateStepInfo(currentStep);
                }
            }
        });


        previousButton = (Button) findViewById(R.id.steps_previous);
        previousButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Do Something
                if (currentStep <= 0){
                    Toast.makeText(getApplicationContext(), "This is the first step.", Toast.LENGTH_LONG).show();
                } else {
                    currentStep--;

                    updateStepInfo(currentStep);
                }

            }
        });

    }

        public void updateStepInfo(int myStepNo){
            myStepVideoUrl = myStepsData.get(myStepNo).getDescription();
            myStepDescription = myStepsData.get(myStepNo).getVideoURL();

            Log.i(ClASS_NAME, myStepVideoUrl);
            Log.i(ClASS_NAME, myStepDescription);

            Bundle bundle = new Bundle();
            bundle.putString("stepurl", myStepVideoUrl);
            bundle.putString("step description", myStepDescription);

            movieFragment.setArguments(bundle);
        }


        public String getMyData() {
            return myStepVideoUrl;
        }


}
