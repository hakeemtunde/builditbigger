package com.corebyte.mob.jokelib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class JsonFileReader {

    private static final String FILE_NAME = "jokes.json";
    private static JsonFileReader sInstance;

    private JsonFileReader() {
    }

    public static JsonFileReader getInstance() {

        if (sInstance == null) {
            sInstance = new JsonFileReader();
        }

        return sInstance;
    }

    public Joke getJoke() {
        List<Joke> jokes = parseToJson(loadJokeData());
        if (jokes.size() == 0) return new Joke(-1, "--No Jokes", "");
        int randnum = (int) (Math.random()*99 + 1);
        return jokes.get(randnum);
    }

    public String loadJokeData() {

        String filedata = null;
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
            int filesize = inputStream.available();
            byte[] buffer = new byte[filesize];
            inputStream.read(buffer);
            inputStream.close();
            filedata = new String(buffer);

        } catch (FileNotFoundException fnf) {
            fnf.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }

        return filedata;
    }

    public List<Joke> parseToJson(String data) {
        List<Joke> jokes = new ArrayList<>();
        if (data == null) return jokes;

        JSONObject jsonObject = new JSONObject(data);
        JSONArray jsonArray = (JSONArray) jsonObject.get("values");

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        jokes = Arrays.asList(gson.fromJson(jsonArray.toString(), Joke[].class));

        return jokes;
    }


}
