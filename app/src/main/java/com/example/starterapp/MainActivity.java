package com.example.starterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //startapp init
        StartAppSDK.init(this, "203975375", true);
        //admob init
        instantiateAdMob();
        setContentView(R.layout.activity_main);
    }

    public void instantiateAdMob(){
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().build());

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                interstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }

    public void handleAdMob(View view) {
        if (interstitialAd.isLoaded()) {
            Intent intent =  new Intent(this, AdActivity.class);
            startActivity(intent);
            interstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
    }

    public void handleStartApp(View view) {
        Intent intent =  new Intent(this, AdActivity.class);
        startActivity(intent);
        StartAppAd.showAd(this);
    }

}
