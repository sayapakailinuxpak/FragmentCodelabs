package com.eldisprojectcodelabs.fragmentcodelabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private boolean isFragmentDisplayed = false;
    static final String FRAGMENT_STATE = "state_of_fragment";
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFragmentDisplayed){
                    displayFragment();
                }else {
                    closeFragment();
                }
            }
        });

        if (savedInstanceState != null){
            isFragmentDisplayed = savedInstanceState.getBoolean(FRAGMENT_STATE);
            if (isFragmentDisplayed){
                //if fragment is displayed, change the button to "close"
                button.setText(R.string.close);
            }
        }

    }

    private void displayFragment(){
        SimpleFragment simpleFragment = SimpleFragment.newInstance();
        //Get the FragmentManager and start transaction
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, simpleFragment).addToBackStack(null).commit(); //add the SimpleFragment
        button.setText(R.string.close);
        isFragmentDisplayed = true; //set boolean flag to indicate fragment is open so we can track state of fragment

    }

    private void closeFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        SimpleFragment simpleFragment = (SimpleFragment) fragmentManager.findFragmentById(R.id.fragment_container); //check to see if fragment already comment
        if (simpleFragment != null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }

        button.setText(R.string.open);
        isFragmentDisplayed = false;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        //save state of fragment (true = open, false = closed)
        outState.putBoolean(FRAGMENT_STATE, isFragmentDisplayed);
        super.onSaveInstanceState(outState);
    }
}
