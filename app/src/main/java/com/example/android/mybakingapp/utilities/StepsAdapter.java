package com.example.android.mybakingapp.utilities;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.mybakingapp.R;
import com.example.android.mybakingapp.data.Steps;

import java.util.ArrayList;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.MyStepsViewHolder> {

    private static String LOG_TAG = StepsAdapter.class.getSimpleName();

    private ArrayList<Steps> myStepsData;
    private Context myContext;
    private String myRecipeName;

    final private ListItemClickListner mOnClickListener;


    public interface ListItemClickListner{
        void onListItemClick(int clickedItemIndex);
    }

    public StepsAdapter(Context context, ArrayList<Steps> StepsData, ListItemClickListner listener, String recipeName){
        myStepsData = StepsData;
        myContext = context;
        mOnClickListener = listener;
        myRecipeName = recipeName;
        Log.i(LOG_TAG, "the number of steps in constructor is " + Integer.toString(myStepsData.size()));
    }

    class MyStepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public TextView shortDescTextView;
        public MyStepsViewHolder(View itemView) {
            super(itemView);
            shortDescTextView = (TextView) itemView.findViewById(R.id.short_desc_steps);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            /* int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);

            Intent intent = new Intent(myContext, StepVideoActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, myStepsData.get(clickedPosition));
            intent.putExtra("recipe_name", recipeName);

            intent.putParcelableArrayListExtra("vlist", myStepsData);

            myContext.startActivity(intent); */

        }
    }

    @NonNull
    @Override
    public StepsAdapter.MyStepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int myLayoutId = R.layout.steps_item;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(myLayoutId, parent, false);
        MyStepsViewHolder myStepsViewHolder = new MyStepsViewHolder(itemView);
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
