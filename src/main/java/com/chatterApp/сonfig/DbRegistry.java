package com.chatterApp.сonfig;

import com.chatterApp.dao.PostsList;

public class DbRegistry {
    private static final DbRegistry dbRegistry = new DbRegistry();
    protected PostsList postsList;

    private static DbRegistry getInstance() {
        return dbRegistry;
    }

    public static PostsList getPostList() {
        return getInstance().postsList;
    }

    public static void setPostSource(PostsList postsList) {
        getInstance().postsList = postsList;
    }
}
