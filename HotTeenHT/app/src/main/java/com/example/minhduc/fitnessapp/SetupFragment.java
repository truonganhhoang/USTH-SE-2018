package com.example.minhduc.fitnessapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SetupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SetupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetupFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int rounds;
    private int restExercises;
    private int restRounds;
    private NumberPicker npRounds;
    private NumberPicker npRestExercises;
    private NumberPicker npRestRounds;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SetupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SetupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SetupFragment newInstance(String param1, String param2) {
        SetupFragment fragment = new SetupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public int getRounds() {
        return rounds;
    }

    public int getRestExercises() {
        return restExercises;
    }

    public int getRestRounds() {
        return restRounds;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_setup, container, false);

        npRounds = rootView.findViewById(R.id.numberPickerRounds);
        npRestExercises = rootView.findViewById(R.id.numberPickerRestExercises);
        npRestRounds = rootView.findViewById(R.id.numberPickerRestRounds);

        rounds = 4;
        npRounds.setWrapSelectorWheel(true);
        npRounds.setMinValue(1);
        npRounds.setMaxValue(20);
        npRounds.setValue(4);
        npRounds.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        npRounds.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                rounds = newVal;
                System.out.println(rounds);
            }
        });

        NumberPicker.Formatter formatter = new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                int temp = value * 5;
                return "" + temp;
            }
        };
        restExercises = 30;
        npRestExercises.setWrapSelectorWheel(true);
        npRestExercises.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        npRestExercises.setFormatter(formatter);
        npRestExercises.setMinValue(1);
        npRestExercises.setMaxValue(40);
        npRestExercises.setValue(6);
        npRestExercises.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                restExercises = newVal * 5;
                System.out.println(restExercises);
            }
        });

        restRounds = 90;
        npRestRounds.setWrapSelectorWheel(true);
        npRestRounds.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        npRestRounds.setFormatter(formatter);
        npRestRounds.setMinValue(1);
        npRestRounds.setMaxValue(40);
        npRestRounds.setValue(18);
        npRestRounds.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                restRounds = newVal * 5;
                System.out.println(restRounds);
            }
        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
