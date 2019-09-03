package com.example.del.cardreaderapp;


import android.graphics.Bitmap;
import android.graphics.Color;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class OtsuBinarize {

    private static Bitmap original, grayscale, binarized;


    OtsuBinarize(Bitmap org){


        original = org;
        grayscale = toGray(original);
        binarized = binarize(grayscale);
    }


    public Bitmap GetBinarized(){

        return binarized;
    }


    // Return histogram of grayscale image
    public static int[] imageHistogram(Bitmap input) {

        int[] histogram = new int[256];

        for(int i=0; i<histogram.length; i++) histogram[i] = 0;

        for(int i=0; i<input.getWidth(); i++) {
            for(int j=0; j<input.getHeight(); j++) {

                int color=input.getPixel(i, j);

                int red = Color.red(color);
                histogram[red]++;
            }
        }

        return histogram;

    }

    // The luminance method
    private static Bitmap toGray(Bitmap original) {

        int alpha, red, green, blue;
        int newPixel;

        Bitmap lum = Bitmap.createBitmap(original.getWidth(), original.getHeight(), Bitmap.Config.ARGB_4444);;

        ArrayList<Integer> Hold=new ArrayList<>();

        for(int i=0; i<original.getWidth(); i++) {
            for(int j=0; j<original.getHeight(); j++) {

                int color=original.getPixel(i, j);

                // Get pixels by R, G, B
                alpha = Color.alpha(color);
                red = Color.red(color);
                green =  Color.green(color);
                blue =  Color.green(color);
                red = (int) (0.21 * red + 0.71 * green + 0.07 * blue);
                // Return back to original format
                newPixel = colorToRGB(alpha, red, red, red);

                // Write pixels into image
                lum.setPixel(i,j,newPixel);
            }
        }

        return lum;

    }

    // Get binary treshold using Otsu's method
    private static int otsuTreshold(Bitmap original) {

        int[] histogram = imageHistogram(original);
        int total = original.getHeight() * original.getWidth();

        float sum = 0;
        for(int i=0; i<256; i++) sum += i * histogram[i];

        float sumB = 0;
        int wB = 0;
        int wF = 0;

        float varMax = 0;
        int threshold = 0;

        for(int i=0 ; i<256 ; i++) {
            wB += histogram[i];
            if(wB == 0) continue;
            wF = total - wB;

            if(wF == 0) break;

            sumB += (float) (i * histogram[i]);
            float mB = sumB / wB;
            float mF = (sum - sumB) / wF;

            float varBetween = (float) wB * (float) wF * (mB - mF) * (mB - mF);

            if(varBetween > varMax) {
                varMax = varBetween;
                threshold = i;
            }
        }

        return threshold;

    }

    private static Bitmap binarize(Bitmap original) {

        int red;
        int newPixel;

        int threshold = otsuTreshold(original);

       Bitmap binarized = original;

        for(int i=0; i<original.getWidth(); i++) {
            for(int j=0; j<original.getHeight(); j++) {


                int color=original.getPixel(i,j);

                // Get pixels
                red =  Color.red(color);
                int alpha =  Color.alpha(color);
                if(red > threshold) {
                    newPixel = 255;
                }
                else {
                    newPixel = 0;
                }
                newPixel = colorToRGB(alpha, newPixel, newPixel, newPixel);
                binarized.setPixel(i, j, newPixel);

            }
        }

        return binarized;

    }

    // Convert R, G, B, Alpha to standard 8 bit
    private static int colorToRGB(int alpha, int red, int green, int blue) {

        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red; newPixel = newPixel << 8;
        newPixel += green; newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;

    }

}