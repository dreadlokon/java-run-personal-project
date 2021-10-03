package com.chatterApp.controllers;

import com.chatterApp.сonfig.DbRegistry;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.BindException;
import java.util.Random;

public class MainControllerTest {

    int testPort;
    MainController sutController;
   // DbRegistry dbRegistryStub

    @Before
    public void setUp() throws Exception {
        //starting server on random port
        DbRegistry.setPostSource(new PostDaoStub());
        testPort = new Random().nextInt(16383) + 49152;
        sutController = new MainController();
        sutController.startServer(testPort);
    }

    @Test
    public void shouldReturnEmptyList() throws IOException {
        //imitate http client
        OkHttpClient client = new OkHttpClient();
        String url = HttpUrl
                .parse("http://localhost:" + testPort + "/posts/search")
                .newBuilder()
                .addQueryParameter("author", "Morty")
                .build()
                .toString();
        Request testRequest = new Request.Builder().url(url).build();
        //try to take response
        Response testResponse = client.newCall(testRequest).execute();
        String responseString = testResponse.body().string();

        Assert.assertEquals("[]", responseString);
    }

    @Test
    public void shouldReturnValidPost() throws IOException {
        //imitate http client
        OkHttpClient client = new OkHttpClient();
        String url = HttpUrl
                .parse("http://localhost:" + testPort + "/posts/search")
                .newBuilder()
                .addQueryParameter("author", "Cat")
                .build()
                .toString();
        Request testRequest = new Request.Builder().url(url).build();
        //try to take response
        Response testResponse = client.newCall(testRequest).execute();
        String responseString = testResponse.body().string();

        String expectedResponse = "\\[\\{\"author\":\"Cat\",\"creationDate\":\\d*,\"message\":\"Meow\"}]";

        Assert.assertTrue(responseString.matches(expectedResponse));
    }

    @Deprecated
    @Test(expected = BindException.class)
    public void shouldThrowBindException() throws IOException {
        sutController.startServer(testPort);
    }

    @After
    public void tearDown() {
        sutController.stopServer(0);
    }
}