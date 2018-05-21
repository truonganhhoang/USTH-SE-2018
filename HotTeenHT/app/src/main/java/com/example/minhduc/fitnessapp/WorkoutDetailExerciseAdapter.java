package com.example.minhduc.fitnessapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class WorkoutDetailExerciseAdapter extends RecyclerView.Adapter<WorkoutDetailExerciseAdapter.ViewHolder> {
    private ArrayList<String> names;
    private ArrayList<Integer> exerciseReps;

    public WorkoutDetailExerciseAdapter(ArrayList<String> names, ArrayList<Integer> exerciseReps) {
        this.names = names;
        this.exerciseReps = exerciseReps;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    @NonNull
    @Override
    public WorkoutDetailExerciseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workout_detail_exercise, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutDetailExerciseAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView textName = cardView.findViewById(R.id.textViewExerciseName);
        TextView textReps = cardView.findViewById(R.id.textViewExerciseReps);
        textName.setText(names.get(position));
        textReps.setText(exerciseReps.get(position) + " REPS");
    }
}
