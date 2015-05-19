package com.example.michal.projektwzorce.presenter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import java.io.ByteArrayOutputStream;
import android.util.Base64;
import android.webkit.WebSettings.ZoomDensity;

import com.example.michal.projektwzorce.R;
import com.example.michal.projektwzorce.model.Photography;

public class Zoom extends Activity {

    WebView mWebView = null;
    private WebView webView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);

        webView = (WebView) findViewById(R.id.webView);

        String html="<html><body><img src='{IMAGE_URL}' /></body></html>";
        Bitmap bitmap = Photography.getInstance().getPhoto();
        getActionBar().setTitle("Powieksz zdjecie");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String imgageBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
        String image = "data:image/png;base64," + imgageBase64;

        html = html.replace("{IMAGE_URL}", image);
        webView.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "utf-8", "");

        webView.setVerticalScrollBarEnabled(true);
        webView.setHorizontalScrollBarEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setDefaultZoom(ZoomDensity.FAR);
        webView.setPadding(0, 0, 0, 0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zoom, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}