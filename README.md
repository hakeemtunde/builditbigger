# Joke App Summary
This app is a joke-telling app. It fetches joke from Google Cloud Endpoints server and display it to the user.

This App was part of my udacity Android developer nanodegree project. I use Gradle to build a joke-telling app, 
factor functionality into libraries and flavors to keep the build simple. 
I also configure a Google Cloud Endpoints development server to supply the jokes. 

## Project components
    
* Project contains a Java library for supplying jokes
* Project contains an Android library with an activity that displays jokes passed to it as intent extras.
* Project contains a Google Cloud Endpoints module that supplies jokes from the Java library. Project loads jokes from GCE module via an `AsyncTask`. Note that this GCE module doesn't need to be deployed to a server. Local testing works fine.
* Project contains connected tests to verify that the `AsyncTask` is indeed loading jokes.
* Project contains paid/free flavors. The paid flavor has no ads, and no unnecessary dependencies.
* Make the free app variant display **interstitial ads** between the main activity and the joke-displaying activity.
* Have the app display a loading indicator while the joke is being fetched from the server.

## Application Behavior 

App retrieves jokes from Google Cloud Endpoints module and displays them via an Activity from the Android Library.
