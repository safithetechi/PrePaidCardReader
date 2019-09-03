package com.example.del.cardreaderapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.util.StringBuilderPrinter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.util.List;


/**
 * Created by DEL on 4/17/2017.
 */

public class OCRthread  {

    Bitmap image;
   TextRecognizer textRecognizer;
    TextView textBlockContent;




    public void SetGoogleAPI(TextRecognizer t){textRecognizer=t; }

    public void SetTextView(TextView textView){ textBlockContent=textView;}


    public void SetImage(Bitmap i){

        image=i;
    }


    public void Run() {




        if(!textRecognizer.isOperational()) {
            IntentFilter lowstorageFilter = new IntentFilter(Intent.ACTION_DEVICE_STORAGE_LOW);



        }


        Frame imageFrame = new Frame.Builder()
                .setBitmap(image)
                .build();

        final StringBuilder[] value = {null};

        textRecognizer.detect(imageFrame);

        textBlockContent.setText("OK");

        textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<TextBlock> detections) {
                final SparseArray<TextBlock> items = detections.getDetectedItems();
                if (items.size() != 0) {

                    textBlockContent.post(new Runnable() {
                        @Override
                        public void run() {
                            value[0] = new StringBuilder();
                            for (int i = 0; i < items.size(); ++i) {
                                TextBlock item = items.valueAt(i);
                                value[0].append(item.getValue());
                                value[0].append("\n");
                            }
                            //update text block content to TextView
                            textBlockContent.setText(value[0].toString());

                        }
                    });
                }

            }
        });





    }




}

