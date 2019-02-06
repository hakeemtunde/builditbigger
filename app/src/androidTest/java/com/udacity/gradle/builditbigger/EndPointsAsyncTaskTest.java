package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Pair;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class EndPointsAsyncTaskTest {

    @Rule public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testMe () throws InterruptedException, ExecutionException, TimeoutException {
        Context context = mainActivityActivityTestRule.getActivity().getApplicationContext();
        //final CountDownLatch latch = new CountDownLatch(1);
        EndPointsAsyncTask asyncTask = new EndPointsAsyncTask() ;
        asyncTask.execute(new Pair<Context, String>(context, "Hakeem"));

        String result = asyncTask.get(30, TimeUnit.SECONDS);
        //latch.await();
        assertNotNull(result);
    }

}