package com.chatterApp.dao;

import com.chatterApp.models.Post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDao implements PostContainer {
    private static final List<Post> postList;

    static {
        postList = new ArrayList<>();
        postList.add(new Post("Arnold", new Date(), "I'll be back"));
        postList.add(new Post("Rick", new Date(), "I'm PICKLE RICK"));
        postList.add(new Post("Rick", new Date(), "Can somebody just let me out of here? If I die in a cage I lose a bet."));
        postList.add(new Post("Arnold", new Date(), "Put that cookie down"));
        postList.add(new Post("Raynor", new Date(), "Say good night, ugly"));
    }

    @Override
    public List<Post> getPosts() {
        return postList;
    }
}
