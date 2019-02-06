package com.corebyte.mob.jokelib;

public class Joker {

    public static String getJoke() {
        return JsonFileReader.getInstance().getJoke().getJoke();
    }
}
