package com.corebyte.mob.jokelib;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class JsonFileTest {

    @Test
    public void loadFileAndParseTest() {
        String data = JsonFileReader.getInstance().loadJokeData();
        assertNotNull(data);

        List<Joke> jokeList = JsonFileReader.getInstance().parseToJson(data);
        assertEquals(100, jokeList.size());
    }

    @Test
    public void getJokerTest() {
        String joke = Joker.getJoke();
        assertThat(joke, containsString("Chuck"));
    }
}
