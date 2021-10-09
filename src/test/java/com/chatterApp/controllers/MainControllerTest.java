package com.chatterApp.controllers;

import com.chatterApp.models.Post;
import com.chatterApp.сonfig.DbRegistry;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.BindException;
import java.util.*;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

public class MainControllerTest {

    int testPort;
    MainController sutController;
    PostDaoStub postSource;

    @Before
    public void setUp() throws Exception {
        postSource = new PostDaoStub();
        DbRegistry.setPostSource(postSource);

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
        assertThat("[]").isEqualTo(responseString);
    }

    @Test
    public void shouldReturnValidPost() throws IOException {
        Post fakePost = new Post("Cat", new Date(), "Meow");
        postSource.add(fakePost);

        //imitate http client
        OkHttpClient client = new OkHttpClient();
        String url = HttpUrl
                .parse(String.format("http://localhost:%d/posts/search", testPort))
                .newBuilder()
                .addQueryParameter("author", "Cat")
                .build()
                .toString();
        Request testRequest = new Request.Builder().url(url).build();
        //try to take response
        Response testResponse = client.newCall(testRequest).execute();
        String responseString = testResponse.body().string();

        List<Post> expectedResponse = new ArrayList<>(Collections.singleton(fakePost));
        assertThatJson(responseString).isEqualTo(expectedResponse);
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