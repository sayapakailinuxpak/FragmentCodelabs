package com.eldisprojectcodelabs.fragmentcodelabs;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.ContentHandler;

import static com.eldisprojectcodelabs.fragmentcodelabs.SimpleFragment.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragment extends Fragment  {
    private static final int YES = 1;
    private static final int NO = 0;
    private static final int NONE = 2;
    int radioButtonChoice = NONE;
    private static final String CHOICE = "choice"; //key

    OnFragmentInteractionListener onFragmentInteractionListener;

    public SimpleFragment() {

    }

    //best practice
    public static SimpleFragment newInstance(int choice){
        SimpleFragment simpleFragment = new SimpleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CHOICE, choice);
        simpleFragment.setArguments(bundle);
        return simpleFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            onFragmentInteractionListener = (OnFragmentInteractionListener) context;
        } else {
            throw new ClassCastException(context.toString() + getResources().getString(R.string.exception_message));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_simple, container, false);
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);
                TextView textView = rootView.findViewById(R.id.fragment_header);

                switch (index){
                    case YES :
                        textView.setText(R.string.yes_message);
                        radioButtonChoice = YES;
                        onFragmentInteractionListener.onRadioButtonChoice(YES);
                        break;
                    case NO :
                        textView.setText(R.string.no_message);
                        radioButtonChoice = NO;
                        onFragmentInteractionListener.onRadioButtonChoice(NO);
                        break;
                    default:
                        radioButtonChoice = NONE;
                        onFragmentInteractionListener.onRadioButtonChoice(NONE);
                        break;
                }
            }
        });

        RatingBar ratingBarA = rootView.findViewById(R.id.ratingbar);
        ratingBarA.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                String ratingInString = String.valueOf(ratingBar.getRating());
                //Toast.makeText(getActivity(), ratingInString, Toast.LENGTH_SHORT).show();
                Toast.makeText(GlobalApplication.getGlobalContext() , ratingInString, Toast.LENGTH_SHORT).show();
            }
        });

        if (getArguments().containsKey(CHOICE)){
            //A choice was made, so get the choice
            radioButtonChoice = getArguments().getInt(CHOICE);
            //Check radio button choice
            if (radioButtonChoice != NONE){
                radioGroup.check(radioGroup.getChildAt(radioButtonChoice).getId());
            }
        }

        return rootView;
    }

    interface OnFragmentInteractionListener{
        void onRadioButtonChoice(int choice);
    }
}
