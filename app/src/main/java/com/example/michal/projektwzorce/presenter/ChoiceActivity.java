package com.example.michal.projektwzorce.presenter;
//cc
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.example.michal.projektwzorce.R;
import com.example.michal.projektwzorce.model.Photography;

import static com.example.michal.projektwzorce.model.Photography.*;

public class ChoiceActivity extends Activity {

    private ImageView takePicture;
    private ImageView getGallery;

    private static Photography photo =  Photography.getInstance();

    private int TAKE_PICTURE = 1;
    private int FROM_GALLERY = 2;



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
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), FROM_GALLERY);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == FROM_GALLERY) {
                Uri selectedImageUri = data.getData();
                photo.setPath(getPath(selectedImageUri));
               // System.out.println("Image Path : " + photo.getPath());
                /*img.setImageURI(selectedImageUri);
                BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
                Funkcje.oryginal = drawable.getBitmap();*/

            }

            if (requestCode == TAKE_PICTURE) {
                Bitmap photo2 = (Bitmap)data.getExtras().get("data");
                photo.setPhoto(photo2);
            }

            //Funkcje.przywrocona=Funkcje.oryginal;
            Intent PictureEdit = new Intent(getApplicationContext(), PictureEdit.class);
            startActivity(PictureEdit);
        }
    }


    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }



}
