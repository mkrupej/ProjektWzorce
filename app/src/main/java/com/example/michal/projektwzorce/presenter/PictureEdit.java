package com.example.michal.projektwzorce.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;

import com.example.michal.projektwzorce.R;
import com.example.michal.projektwzorce.model.Photography;

public class PictureEdit extends Activity
{

   // private static Photography photo =  Photography.getInstance();

    ImageView Picture;
    TextView Tekst;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_edit);
        Picture=(ImageView)findViewById(R.id.Pic);
        Tekst=(TextView)findViewById(R.id.Text);
            Picture.setImageBitmap(Photography.getInstance().Picture);
        if (Photography.getInstance().Picture == null)
            Tekst.setText("WIELKI KUTAS");
     //   Picture.setImageBitmap(photo.getPhoto());
            //Picture.setImageURI(photo.getPath());





    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_picture_edit, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.seekbar1:

                settings();

                return true;

            case R.id.seekbar2:

                settings();

                return true;

            case R.id.przyciecie:

                settings();

                return true;

            case R.id.home:

                home();

                return true;

            case R.id.facebook:

                facebook();

                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }
    public void settings()
    {
        Toast.makeText(this, "Home Option Selexted", Toast.LENGTH_SHORT).show();
    }
    public void home()
    {
        Intent back = new Intent(PictureEdit.this,MainActivity.class);
        PictureEdit.this.startActivity(back);
    }
    public void facebook()
    {
        Toast.makeText(this, "Android Option Selexted", Toast.LENGTH_SHORT).show();
    }
}

