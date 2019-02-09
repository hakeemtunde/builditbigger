package com.udacity.gradle.builditbigger.free;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.R;

public class MainActivityFragment extends Fragment {

    private InterstitialAd mInterstitialAd;
    private MainActivityFragmentListener mainActivityFragmentListener;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mInterstitialAd = new InterstitialAd(getContext());

//        mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        final AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                //load the joke
                if (mainActivityFragmentListener != null) {
                    MainActivity mainActivity = (MainActivity) mainActivityFragmentListener;
                    mainActivity.loadJoke();
                    mInterstitialAd.loadAd(adRequest);
                }
            }
        });

        return root;

    }

    public InterstitialAd getInterstitialAd() {
        return mInterstitialAd;
    }

    public void setMainActivityFragmentListener(MainActivityFragmentListener listener) {
        mainActivityFragmentListener = listener;
    }

    public interface MainActivityFragmentListener {
        public void showInterstitialAd();
    }

}
