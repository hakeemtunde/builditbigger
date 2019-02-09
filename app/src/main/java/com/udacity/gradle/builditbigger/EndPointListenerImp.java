package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;

import com.corebyte.mob.jokeui.JokeDisplayActivity;

public class EndPointListenerImp implements EndPointsAsyncTask.EndPointListener {

    private Context mContext;
    private ProgressBar mProgressBar;

    public EndPointListenerImp(Context context, ProgressBar progressBar) {
        mContext = context;
        mProgressBar = progressBar;
    }

    @Override
    public void onComplete(String joke) {
        Intent intent = new Intent(mContext, JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayActivity.JOKE_TEXT_KEY, joke);
        mContext.startActivity(intent);
    }

    @Override
    public void loading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishLoading() {
        mProgressBar.setVisibility(View.GONE);
    }
}
