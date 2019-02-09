package com.udacity.gradle.builditbigger.free;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.builditbigger.EndPointListenerImp;
import com.udacity.gradle.builditbigger.EndPointsAsyncTask;
import com.udacity.gradle.builditbigger.R;

public class MainActivity extends AppCompatActivity
        implements MainActivityFragment.MainActivityFragmentListener {

    private ProgressBar mProgressBar;
    private MainActivityFragment mainActivityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, getString(R.string.admob_app_id));

        mProgressBar = (ProgressBar) findViewById(R.id.loading);
        mProgressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void tellJoke(View view) {
        showInterstitialAd();
    }


    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof MainActivityFragment) {
            mainActivityFragment = (MainActivityFragment) fragment;
            mainActivityFragment.setMainActivityFragmentListener(this);
        }
    }

    public void loadJoke() {
        //api call
        EndPointListenerImp endPointListenerImp = new EndPointListenerImp(this, mProgressBar);
        new EndPointsAsyncTask(endPointListenerImp).execute();
    }

    @Override
    public void showInterstitialAd() {

        if (mainActivityFragment != null) {
            if (mainActivityFragment.getInterstitialAd() != null
                    && mainActivityFragment.getInterstitialAd().isLoaded()) {
                mainActivityFragment.getInterstitialAd().show();
            }
        }
    }
}
