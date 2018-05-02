package com.example.minhduc.fitnessapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;

public class ExerciseWithRepsAdapter extends RecyclerView.Adapter<ExerciseWithRepsAdapter.ViewHolder> {
    private int purpose;
    private ArrayList<String> names;
    private ArrayList<Integer> exerciseReps;

    public ExerciseWithRepsAdapter(int purpose, ArrayList<String> names, ArrayList<Integer> exerciseReps) {
        this.purpose = purpose;
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
    public ExerciseWithRepsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_with_reps, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseWithRepsAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView textView = cardView.findViewById(R.id.exerciseName);
        NumberPicker np = cardView.findViewById(R.id.numberPickerExerciseReps);
        np.setMinValue(0);
        np.setMaxValue(99);
        np.setWrapSelectorWheel(true);
        final int pos = position;
        textView.setText(names.get(position));

        switch (purpose) {
            case 0:
                //np.setValue(10);
                np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        exerciseReps.set(pos, newVal);
                    }
                });

                break;
            case 1:
                np.setValue(exerciseReps.get(position));
                break;
        }
    }
}
