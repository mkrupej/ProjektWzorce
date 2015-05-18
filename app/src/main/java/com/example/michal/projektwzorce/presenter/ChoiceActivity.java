package com.example.michal.projektwzorce.presenter;
//cc
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.michal.projektwzorce.R;

public class ChoiceActivity extends Activity {

    private ImageView takePicture;
    private ImageView getGallery;

    private int TAKE_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        takePicture = (ImageView)findViewById(R.id.imageCameraChoice);

       takePicture.setOnClickListener(new View.OnClickListener() {
           public void onClick(View arg0) {
               Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
               startActivityForResult(cameraIntent, TAKE_PICTURE);
           }
       });

        getGallery = (ImageView)findViewById(R.id.imageGalleryChoice);
        getGallery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), TAKE_PICTURE);
            }
        });
    }




}
