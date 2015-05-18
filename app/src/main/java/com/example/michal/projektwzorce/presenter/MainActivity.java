package com.example.michal.projektwzorce.presenter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.example.michal.projektwzorce.R;
public class MainActivity extends Activity
{
    private static int CZAS = 2000; //3 sekundy

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Uruchom w¹tek otwieraj¹cy g³ówn¹ aktywnoœæ
        ActivityStarter starter = new ActivityStarter();
        starter.start();
    }

    private class ActivityStarter extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(CZAS);
            } catch (Exception e) {
                Log.e("SplashScreen", e.getMessage());
            }
            Intent intent = new Intent(MainActivity.this, ChoiceActivity.class);
            MainActivity.this.startActivity(intent);
            MainActivity.this.finish();
        }
    }
}


