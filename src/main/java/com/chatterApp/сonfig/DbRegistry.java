package com.chatterApp.сonfig;

import com.chatterApp.dao.PostContainer;

public class DbRegistry {
    private static final DbRegistry dbRegistry = new DbRegistry();
    protected PostContainer postContainer;

    private static DbRegistry getInstance() {
        return dbRegistry;
    }

    public static PostContainer getPostList() {
        return getInstance().postContainer;
    }

    public static void setPostSource(PostContainer postContainer) {
        getInstance().postContainer = postContainer;
    }
}
