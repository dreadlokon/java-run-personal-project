package com.chatterApp.services;

import com.chatterApp.сonfig.DbRegistry;
import com.chatterApp.dao.PostsList;
import com.chatterApp.models.Post;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class SearchService {
    static PostsList postsList = DbRegistry.getPostList();

    public static List<Post> searchPosts(Map<String, String> parametersMap) {
        //some scalable search logic in future
        return searchByAuthor(parametersMap);
    }

    public static List<Post> searchByAuthor(Map<String, String> parametersMap) {
        if (!parametersMap.containsKey("author")) {
            return Collections.emptyList();
        }
        String parameterValue = parametersMap.get("author");

        return postsList.getPosts().stream()
                .filter(post -> post.getAuthor().equals(parameterValue))
                .collect(toList());
    }
}
