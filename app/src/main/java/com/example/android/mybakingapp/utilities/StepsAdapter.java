package com.example.android.mybakingapp.utilities;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.mybakingapp.EachStepActivity;
import com.example.android.mybakingapp.R;
import com.example.android.mybakingapp.data.Steps;

import java.util.ArrayList;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.MyStepsViewHolder> {

    private static String LOG_TAG = StepsAdapter.class.getSimpleName();

    private ArrayList<Steps> myStepsData;
    private Context myContext;
    private String myRecipeName;

    private StepsClickListener stepCallBack;

    private boolean isTablet;


    public interface StepsClickListener{
        void StepOnClick(int clickedItemIndex);
    }

    public StepsAdapter(Context context, ArrayList<Steps> StepsData, String recipeName, StepsClickListener listener){
        myStepsData = StepsData;
        myContext = context;
        myRecipeName = recipeName;
        stepCallBack = listener;
    }

    class MyStepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView shortDescTextView;
        StepsClickListener myStepsListner;

        public MyStepsViewHolder(View itemView, StepsClickListener stepsClickListener) {
            super(itemView);
            shortDescTextView = (TextView) itemView.findViewById(R.id.short_desc_steps);
            myStepsListner = stepsClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int clickedPosition = getAdapterPosition();
            myStepsListner.StepOnClick(clickedPosition);

        }
    }

    @NonNull
    @Override
    public StepsAdapter.MyStepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int myLayoutId = R.layout.steps_item;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(myLayoutId, parent, false);
        MyStepsViewHolder myStepsViewHolder = new MyStepsViewHolder(itemView, stepCallBack);
        return myStepsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StepsAdapter.MyStepsViewHolder holder, int position) {

        String mySteps = myStepsData.get(position).getShortDescription();
        Log.i(LOG_TAG, mySteps);
        holder.shortDescTextView.setText(mySteps);

    }

    @Override
    public int getItemCount() {
        if (myStepsData == null){
            return 0;
        }
        return myStepsData.size();
    }
}
