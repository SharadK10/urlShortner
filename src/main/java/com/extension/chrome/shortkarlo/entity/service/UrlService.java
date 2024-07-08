package com.extension.chrome.shortkarlo.entity.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.extension.chrome.shortkarlo.entity.UrlMapping;
import com.extension.chrome.shortkarlo.repository.UrlRepository;
import com.extension.chrome.shortkarlo.util.HashUtil;

@Service
public class UrlService {
	
	@Autowired
	private UrlRepository repository;
	
	@Value("${domain}")
    private String domain;
	
	public String convertOriginalToShortUrl(String originalUrl) {
		
		String hash = HashUtil.md5(originalUrl);
		String generatedHash = HashUtil.shortenHash(hash);
		String shortUrl = domain + generatedHash;
		UrlMapping mapping = new UrlMapping(originalUrl, generatedHash, LocalDate.now());
		repository.save(mapping);
		return shortUrl;
		
	}
	
	public String getOriginalUrl(String hash) {
		UrlMapping mapping = repository.findByShortUrl(hash);
		return mapping.getOriginalUrl();
	}

}
