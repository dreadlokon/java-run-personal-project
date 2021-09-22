package com.chatterApp.controllers;

import com.chatterApp.models.Post;
import com.chatterApp.services.SearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchHandler implements HttpHandler {
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod() != null && "GET".equals(exchange.getRequestMethod())) {

            Map<String, String> requestParameters = getParsedParameters(exchange.getRequestURI().getRawQuery());
            List<Post> responseList = SearchService.searchPosts(requestParameters);

            String jsonResponse = this.jsonMapper.writeValueAsString(responseList);
            byte[] responseBytes = jsonResponse.getBytes(StandardCharsets.UTF_8);

            exchange.sendResponseHeaders(200, responseBytes.length);
            OutputStream responseStream = exchange.getResponseBody();
            responseStream.write(responseBytes);
            responseStream.close();

        } else {
            exchange.sendResponseHeaders(405, -1);
        }
        exchange.close();
    }

    //Simple parameters parsing without arrays in values
    private Map<String, String> getParsedParameters(String query) {
        if (query == null || query.equals("")) {
            return Collections.emptyMap();
        }
        Map<String, String> queryParameters = new HashMap<>();
        String[] parameters = query.split("&");
        for (String current : parameters) {
            String[] splited = current.split("=");
            if (splited[0] != null && splited[1] != null) {
                queryParameters.put(splited[0], splited[1]);
            }
        }
        return queryParameters;
    }


}
