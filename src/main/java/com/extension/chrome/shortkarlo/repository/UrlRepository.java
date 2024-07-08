package com.extension.chrome.shortkarlo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.extension.chrome.shortkarlo.entity.UrlMapping;

@Repository
public interface UrlRepository extends JpaRepository<UrlMapping, Long> {
	public UrlMapping findByShortUrl(String shortUrl);
}
