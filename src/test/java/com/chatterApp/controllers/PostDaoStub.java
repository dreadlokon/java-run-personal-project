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
    }

    @Override
    public List<Post> getPosts() {
        return postList;
    }

    public void add (Post post) {
        postList.add(post);
    }
}
