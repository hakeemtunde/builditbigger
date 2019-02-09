package com.corebyte.mob.jokelib;

public class Joke {

    private int id;
    private String joke;
    private String category;

    public Joke() {
    }

    public Joke(int id, String joke, String category) {
        this.id = id;
        this.joke = joke;
        this.category = category;
    }

    public String getJoke() {
        return joke;
    }

}
