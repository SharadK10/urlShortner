package com.extension.chrome.shortkarlo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@SpringBootApplication
public class ShortkarloApplication {
	
	private static final String API_URL = "https://leetcode.com/contest/api/ranking/weekly-contest-405/?pagination=2&region=global";
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(API_URL));

        int numberOfRequests = 5;
        List<CompletableFuture<String>> futures = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < numberOfRequests; i++) {
            futures.add(CompletableFuture.supplyAsync(() -> makeApiCall(restTemplate), executor));
        }

        List<String> responses = new ArrayList<>();
        for (CompletableFuture<String> future : futures) {
            try {
                responses.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                responses.add("Exception: " + e.getMessage());
            }
        }

        executor.shutdown();

        // Print all responses
        for (String response : responses) {
            System.out.println(response);
        }
    }

    private static String makeApiCall(RestTemplate restTemplate) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity("/", String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                return "Error: " + response.getStatusCode();
            }
        } catch (Exception e) {
            return "Exception: " + e.getMessage();
        }
    }

}
