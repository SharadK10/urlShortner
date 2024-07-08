package com.extension.chrome.shortkarlo.entity.controller;

import java.net.URI;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.extension.chrome.shortkarlo.entity.service.UrlService;

@Controller
public class UrlController {

	@Autowired
	private UrlService service;

	@RequestMapping("/")
	@ResponseBody
	public HashMap<Integer, String> getHello() {
		HashMap<Integer, String> map = new HashMap<>();
		map.put(1, "Hi");
		return map;
	}

	@RequestMapping(value = "/short-url", method = RequestMethod.POST)
	@ResponseBody
	public String getShortUrl(String originalUrl) {
		System.out.println(originalUrl);
		String shortUrl = service.convertOriginalToShortUrl(originalUrl);
		System.out.println(shortUrl);
		return shortUrl;
	}

	@RequestMapping(value = "/{shorturl}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shorturl) {
		// System.out.println(shorturl);
		String originalUrl = service.getOriginalUrl(shorturl);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create(originalUrl));
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);

	}

}
