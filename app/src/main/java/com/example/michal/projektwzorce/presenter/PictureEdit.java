package com.example.michal.projektwzorce.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;
import android.view.MenuItem;

import com.example.michal.projektwzorce.R;

public class PictureEdit extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_edit);

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

                homeActivity();

                return true;

            case R.id.seekbar2:

                homeActivity();

                return true;

            case R.id.przyciecie:

                homeActivity();

                return true;

            case R.id.home:

                javaActivity();

                return true;

            case R.id.facebook:

                androidActivity();

                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }
    public void homeActivity()
    {
        Toast.makeText(this, "Home Option Selexted", Toast.LENGTH_SHORT).show();
    }
    public void javaActivity()
    {
        Toast.makeText(this, "Java Option Selexted", Toast.LENGTH_SHORT).show();
    }
    public void androidActivity()
    {
        Toast.makeText(this, "Android Option Selexted", Toast.LENGTH_SHORT).show();
    }
}

