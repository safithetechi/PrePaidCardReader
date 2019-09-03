package com.example.del.cardreaderapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_result);
        Intent intent =getIntent();
        String value=intent.getStringExtra("Number");

        int i=0;

        String NewString = "";

        for(i=0;i<value.length();i++) {

            if (value != "") {

                if (' ' != value.charAt(i)) {

                      NewString +=value.charAt(i) ;
                }
            }

            else{
                break;
            }
        }


        HorizontalScrollView horizontalScrollView= (HorizontalScrollView) findViewById(R.id.scrollview);

        LinearLayout linearLayout =(LinearLayout) findViewById(R.id.linearlayout);

        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        //horizontalScrollView.addView(linearLayout);

        int TotalLength=NewString.length();

        NumberPicker[] numberPicker= new NumberPicker[TotalLength] ;

            Log.d("Pic", "onCreate: "+NewString.length());

        for(i=0;i<NewString.length();i++){
            numberPicker[i]=new NumberPicker(this);
            numberPicker[i].setPadding(0,0,0,0);
            numberPicker[i].setMinValue(0);
            numberPicker[i].setMaxValue(9);
           numberPicker[i].setWrapSelectorWheel(false);


            Log.d("Pic", "onCreate: "+((int)NewString.charAt(i)));

            numberPicker[i].setValue((int)(NewString.charAt(i)-'0'));

            linearLayout.addView(numberPicker[i]);

        }


    }

}
