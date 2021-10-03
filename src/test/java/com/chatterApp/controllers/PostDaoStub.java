package com.chatterApp.controllers;

import com.chatterApp.dao.PostContainer;
import com.chatterApp.models.Post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDaoStub implements PostContainer {
    private static final List<Post> postList;

    static {
        postList = new ArrayList<>();
        postList.add(new Post("Cat", new Date(), "Meow"));
        postList.add(new Post("Dog", new Date(), "BowWow"));
    }

    @Override
    public List<Post> getPosts() {
        return postList;
    }
}
