package com.example.del.cardreaderapp;
import android.content.Intent;
        import android.content.Context;
        import android.content.IntentFilter;
        import android.content.res.AssetManager;
        import android.database.Cursor;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Rect;
        import android.net.Uri;
        import android.os.AsyncTask;
        import android.os.Build;
        import android.os.Environment;
        import android.provider.MediaStore;
        import android.support.v4.content.FileProvider;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import 	java.text.SimpleDateFormat;

        import android.util.SparseArray;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.NumberPicker;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.vision.Detector;
        import com.google.android.gms.vision.Frame;
        import com.google.android.gms.vision.text.TextBlock;
        import com.google.android.gms.vision.text.TextRecognizer;
        import com.theartofdev.edmodo.cropper.CropImage;
        import com.theartofdev.edmodo.cropper.CropImageView;


        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.util.Date;


public class MainActivity extends AppCompatActivity {

    Button CameraButton;
    Button CropButton;
    Button NumberButton;
    Bitmap image;
    int count=0;

    TextRecognizer textRecognizer;
    int REQUEST_TAKE_PHOTO=1;
    static int REQUEST_IMAGE_CAPTURE=1;
    String datapath;
    String mCurrentPhotoPath;
    Uri photoURI;
    String OCRresult;
    boolean CameraLunched=false;
    int chk=0;

    int LengthOfImage;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







        CameraButton = (Button) findViewById(R.id.CameraButton);

        CropButton =(Button) findViewById(R.id.Crop);

        CropButton.setEnabled(false);

        NumberButton = (Button) findViewById(R.id.numberpicker);

        NumberButton.setEnabled(false);

        Button ResetButton = (Button) findViewById(R.id.button);


        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Reset(view);
            }
        });


    }


    public void ChangeImage(View view){



        count++;
        if(count==1){
            image = BitmapFactory.decodeResource(getResources(), R.drawable.c_1);


            ImageView imageView = (ImageView) findViewById(R.id.SelectedImage);
            imageView.setImageResource(R.drawable.c_1);
        }


        else if(count==2){
            image = BitmapFactory.decodeResource(getResources(), R.drawable.c_2);


            ImageView imageView = (ImageView) findViewById(R.id.SelectedImage);
            imageView.setImageResource(R.drawable.c_2);

        }


        else if(count==3){
            image = BitmapFactory.decodeResource(getResources(), R.drawable.c_3);


            ImageView imageView = (ImageView) findViewById(R.id.SelectedImage);
            imageView.setImageResource(R.drawable.c_3);

        }


        else if(count==4){
            image = BitmapFactory.decodeResource(getResources(), R.drawable.c_4);


            ImageView imageView = (ImageView) findViewById(R.id.SelectedImage);
            imageView.setImageResource(R.drawable.c_4);

        }

        else if(count==5){
            image = BitmapFactory.decodeResource(getResources(), R.drawable.c_5);


            ImageView imageView = (ImageView) findViewById(R.id.SelectedImage);
            imageView.setImageResource(R.drawable.c_5);

        }

        else if(count==6){
            image = BitmapFactory.decodeResource(getResources(), R.drawable.c_6);


            ImageView imageView = (ImageView) findViewById(R.id.SelectedImage);
            imageView.setImageResource(R.drawable.c_6);

        }

        else if(count==7){
            image = BitmapFactory.decodeResource(getResources(), R.drawable.c_7);


            ImageView imageView = (ImageView) findViewById(R.id.SelectedImage);
            imageView.setImageResource(R.drawable.c_7);

        }

        else if(count==8){
            image = BitmapFactory.decodeResource(getResources(), R.drawable.c_8);


            ImageView imageView = (ImageView) findViewById(R.id.SelectedImage);
            imageView.setImageResource(R.drawable.c_8);

        }


        else if(count==9){
            image = BitmapFactory.decodeResource(getResources(), R.drawable.c_9);


            ImageView imageView = (ImageView) findViewById(R.id.SelectedImage);
            imageView.setImageResource(R.drawable.c_9);



        }


        else if(count==10){
            image = BitmapFactory.decodeResource(getResources(), R.drawable.c_10);


            ImageView imageView = (ImageView) findViewById(R.id.SelectedImage);
            imageView.setImageResource(R.drawable.c_10);



        }

        else if(count==11){
            image = BitmapFactory.decodeResource(getResources(), R.drawable.c_11);


            ImageView imageView = (ImageView) findViewById(R.id.SelectedImage);
            imageView.setImageResource(R.drawable.c_11);



        }


        else if(count==12){
            image = BitmapFactory.decodeResource(getResources(), R.drawable.c_12);


            ImageView imageView = (ImageView) findViewById(R.id.SelectedImage);
            imageView.setImageResource(R.drawable.c_12);



        }

        else if(count==13){
            image = BitmapFactory.decodeResource(getResources(), R.drawable.c_13);


            ImageView imageView = (ImageView) findViewById(R.id.SelectedImage);
            imageView.setImageResource(R.drawable.c_13);



        }


        else if(count==14){
            image = BitmapFactory.decodeResource(getResources(), R.drawable.c_14);


            ImageView imageView = (ImageView) findViewById(R.id.SelectedImage);
            imageView.setImageResource(R.drawable.c_14);



        }



        else if(count==15){
            image = BitmapFactory.decodeResource(getResources(), R.drawable.c_15);


            ImageView imageView = (ImageView) findViewById(R.id.SelectedImage);
            imageView.setImageResource(R.drawable.c_15);

            count=0;

        }

    }


    public void CropImage(View view){

        CropImage.activity(photoURI)
                .setGuidelines(CropImageView.Guidelines.OFF)
                .start(this);


        CropButton.setEnabled(true);

    }



    public void Launch(View view){

        Intent intent = new Intent(this,ResultActivity.class);
        //intent.putExtra()
        intent.putExtra("Number",OCRresult);
        this.startActivity(intent);




    }



    public void DoOCR(View view) throws InterruptedException {

        //OCRthread ocRthread=new OCRthread();


//        Bitmap temp=Bitmap.createScaledBitmap(image, 212, 52, true);

        textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();





        if(!textRecognizer.isOperational()) {
            // Note: The first time that an app using a Vision API is installed on a
            // device, GMS will download a native libraries to the device in order to do detection.
            // Usually this completes before the app is run for the first time.  But if that
            // download has not yet completed, then the above call will not detect any text,
            // barcodes, or faces.
            // isOperational() can be used to check if the required native libraries are currently
            // available.  The detectors will automatically become operational once the library
            // downloads complete on device.

            // Check for low storage.  If there is low storage, the native library will not be
            // downloaded, so detection will not become operational.
            IntentFilter lowstorageFilter = new IntentFilter(Intent.ACTION_DEVICE_STORAGE_LOW);
            boolean hasLowStorage = registerReceiver(null, lowstorageFilter) != null;

            if (hasLowStorage) {
                Toast.makeText(this,"Low Storage", Toast.LENGTH_LONG).show();
            }
        }



        OtsuBinarize Otsu = new OtsuBinarize(image);

        Bitmap temp=Otsu.GetBinarized();

        Frame imageFrame = new Frame.Builder()
                .setBitmap(temp)
                .build();



        SparseArray<TextBlock> textBlocks = textRecognizer.detect(imageFrame);


        String string="";

        for (int i = 0; i < textBlocks.size(); i++) {
            TextBlock textBlock = textBlocks.get(textBlocks.keyAt(i));

            string+=textBlock.getValue();
            // Do something with value
        }

        TextView textBlockContent= (TextView) findViewById(R.id.OcrText);

        textBlockContent.setText(string);

        ImageView imageView = (ImageView) findViewById(R.id.SelectedImage);
        imageView.setImageBitmap(temp);




        NumberButton.setEnabled(true);

    }


    public void CallCameraActivity(View view) {


        TextView textView = (TextView) findViewById(R.id.OcrText);

        textView.setText("Working!!");

        MarshMelloPermission marshMallowPermission = new MarshMelloPermission(this);


        if (!marshMallowPermission.checkPermissionForCamera()) {
            marshMallowPermission.requestPermissionForCamera();
        } else {
            if (!marshMallowPermission.checkPermissionForExternalStorage()) {
                marshMallowPermission.requestPermissionForExternalStorage();


            } else {


                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    textView.setText("photoUri Is set");
                    try {
                        textView.setText("photoUri Is set");
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                        textView.setText("Somthing went wrong");


                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {

                        photoURI = Uri.fromFile(photoFile);


                        textView.setText("photoUri Is set");
                        //Uri photoURI = FileProvider.getUriForFile(this,
                        //      "com.example.android.fileprovider",
                        //    photoFile);


                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);


                    }


                }
            }


        }


        CameraButton.setText("Crop Image");

        CameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage(view);
            }
        });


    }




    public void Reset(View view){


        CameraButton.setText("Take Picture");

        CameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallCameraActivity(view);

            }
        });




    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {



        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                photoURI= result.getUri();
                CameraButton.setText("OCR");

                CameraButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            DoOCR(view);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });




            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }


            try {
                image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                CameraLunched = false;
            } catch (IOException e) {
                e.printStackTrace();
            }


            ImageView imageView = (ImageView) findViewById(R.id.SelectedImage);
            imageView.setImageBitmap(image);


        }



        /*
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView mImageView = (ImageView) findViewById(R.id.SelectedImage);
            mImageView.setImageBitmap(imageBitmap);
        }
        */
    }


}
