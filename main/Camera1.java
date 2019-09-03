package com.example.del.cardreaderapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Camera;
import android.hardware.camera2.*;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Camera1 extends SurfaceView implements SurfaceHolder.Callback, Camera.ShutterCallback, Camera.PictureCallback {


    Camera camera;
    SurfaceHolder mHolder;
    SurfaceView surfaceView;

    public Camera1(Context context, Camera c) {
        super(context);

        camera=c;

       // SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceView);

        mHolder=getHolder();
        //mHolder.
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }



    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        try {
            camera.setPreviewDisplay(mHolder);
            camera.startPreview();
        } catch (IOException e) {
            Log.d("Pic", "Error setting camera preview: " + e.getMessage());
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        if (mHolder.getSurface() == null){
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try {
            camera.stopPreview();
        } catch (Exception e){
            // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here

        // start preview with new settings
        try {



            camera.setPreviewDisplay(mHolder);
            camera.startPreview();

        } catch (Exception e){
            Log.d("Pic", "Error starting camera preview: " + e.getMessage());
        }
    }



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // crop view
        int actualPreviewWidth = getResources().getDisplayMetrics().widthPixels;
        int actualPreviewHeight = getResources().getDisplayMetrics().heightPixels;


        if (surfaceView != null) {
            surfaceView.layout(0, 0, actualPreviewWidth, actualPreviewHeight);
        }
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void onShutter() {

    }

    @Override
    public void onPictureTaken(byte[] bytes, Camera camera) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint p= new Paint(Color.RED);
        Log.d(TAG,"draw");
        canvas.drawText("PREVIEW", canvas.getWidth()/2, canvas.getHeight()/2, p );
    }




}
