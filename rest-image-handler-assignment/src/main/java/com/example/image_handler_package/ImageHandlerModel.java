package com.example.image_handler_package;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity(name = "image_data")
public class ImageHandlerModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String originalImageName;
	
	private String resizedImageName;
	
	
	@Lob
	private byte[] originalImageData;
	
	@Lob
	private byte[] resizedImageData;
	
	private String contentType;

	public long getId() {
		return id;
	}

	public String getOriginalImageName() {
		return originalImageName;
	}

	public String getResizedImageName() {
		return resizedImageName;
	}

	public byte[] getOriginalImageData() {
		return originalImageData;
	}

	public byte[] getResizedImageData() {
		return resizedImageData;
	}

	public String getContentType() {
		return contentType;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setOriginalImageName(String originalImageName) {
		this.originalImageName = originalImageName;
	}

	public void setResizedImageName(String resizedImageName) {
		this.resizedImageName = resizedImageName;
	}

	public void setOriginalImageData(byte[] originalImageData) {
		this.originalImageData = originalImageData;
	}

	public void setResizedImageData(byte[] resizedImageData) {
		this.resizedImageData = resizedImageData;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	
}
