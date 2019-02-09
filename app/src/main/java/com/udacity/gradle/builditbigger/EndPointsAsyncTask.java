package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndPointsAsyncTask extends AsyncTask<Void, Void, String> {

    private static MyApi myApiService = null;
    private EndPointListener mListener;

    public EndPointsAsyncTask() {}

    public EndPointsAsyncTask(EndPointListener listener) {
        mListener = listener;
    }

    @Override
    protected void onPreExecute() {
        if(mListener != null){
            mListener.loading();
        }
    }

    @Override
    protected String doInBackground(Void... voids) {

        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(
                    AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        try {
            return myApiService.sayJoke().execute().getData();
        } catch (IOException e) {
            Log.e(EndPointsAsyncTask.class.getSimpleName(), e.getMessage());
            mListener.finishLoading();
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (mListener != null) {
            mListener.finishLoading();
            mListener.onComplete(result);
        }
    }

    public interface EndPointListener {
        public void onComplete(String joke);
        public void loading();
        public void finishLoading();

    }
}
