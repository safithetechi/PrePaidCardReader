package com.example.del.cardreaderapp;

import android.graphics.Canvas;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Camera;
import android.hardware.camera2.*;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;

public class CameraActivity extends AppCompatActivity  {


    private Camera1 CamPreview;
    private Camera camera;
   // private Canvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        camera = getCameraInstance();

        CamPreview = new Camera1(this,camera);

        FrameLayout surfaceView = (FrameLayout )findViewById(R.id.surfaceView);

        surfaceView.addView(CamPreview);




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {


            //this.camera = new Camera2(getContext());
        }

        else {

            //this.camera = new Camera1(getContext());
        }




    }



    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }



}
