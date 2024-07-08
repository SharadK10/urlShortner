package com.extension.chrome.shortkarlo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UrlMapping {
	@Id
	@GeneratedValue
	private long id;
	private String originalUrl;
	private String shortUrl;
	private LocalDate createTime;

	public UrlMapping() {
		super();
	}

	public UrlMapping(String originalUrl, String shortUrl, LocalDate createTime) {
		super();
		this.originalUrl = originalUrl;
		this.shortUrl = shortUrl;
		this.createTime = createTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public LocalDate getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDate createTime) {
		this.createTime = createTime;
	}

}
