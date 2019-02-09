package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class EndPointsAsyncTaskTest {


    @Test
    public void endPointsAsyncTaskTest() throws InterruptedException, ExecutionException, TimeoutException {
        EndPointsAsyncTask asyncTask = new EndPointsAsyncTask();
        asyncTask.execute();
        String result = asyncTask.get(30, TimeUnit.SECONDS);
        assertNotNull(result);
        assertFalse(TextUtils.isEmpty(result));
        assertThat(result, containsString("Chuck"));
    }

}