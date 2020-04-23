package com.eldisprojectcodelabs.fragmentcodelabs;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.ContentHandler;


/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragment extends Fragment {
    private static final int YES = 1;
    private static final int NO = 0;


    public SimpleFragment() {

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
                        break;
                    case NO :
                        textView.setText(R.string.no_message);
                        break;
                    default:
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

        return rootView;
    }
}
