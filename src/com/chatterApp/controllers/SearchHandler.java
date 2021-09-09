package com.chatterApp.controllers;

import com.chatterApp.dao.PostDao;
import com.chatterApp.models.Post;
import com.fasterxml.jackson.databind.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class SearchHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("GET")) {

            Map<String, String> requestParameters = getParsedParameters(exchange.getRequestURI().getRawQuery());
            List<Post> responseList = getPostsByParameter(requestParameters,"author");
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(responseList);
            byte[] buffer = jsonResponse.getBytes();
            exchange.sendResponseHeaders(200, buffer.length);
            OutputStream responseStream = exchange.getResponseBody();
            responseStream.write(buffer);
            responseStream.close();
        } else exchange.sendResponseHeaders(405, -1);
        exchange.close();
    }

    //Simple parameters parsing without arrays in values
    Map<String,String> getParsedParameters(String query) {
        if (query == null || query.equals("")) return Collections.emptyMap();
        Map<String,String> queryParameters = new HashMap<>();
        String[] parameters = query.split("&");
        for (String current : parameters) {
            String[] splited = current.split("=");
            if (splited[0] != null && splited[1] != null) {
                queryParameters.put(splited[0],splited[1]);
            }
        }
        return queryParameters;
    }

    List<Post> getPostsByParameter (Map<String,String> parametersMap, String parameter) {
        List<Post> postList = new ArrayList<>();
        if (!parametersMap.containsKey(parameter)) {
            return Collections.emptyList();
                }
        String value = parametersMap.get(parameter);
        for (Post current : PostDao.postList) {
            // Here search for author only
            if (value.equals(current.getAuthor())) {
                postList.add(current);
            }
        }
        return postList;
    }
}
