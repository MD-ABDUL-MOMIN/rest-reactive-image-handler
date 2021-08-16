package com.example.responsehelper;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class ImageResponse {
	
	private String imageName;
	private  String imageUri;
	
	private String contentType;

	

	public ImageResponse(String originalImageName, String originalImageUri, String contentType) {
		this.contentType =contentType;
		this.imageName = originalImageName;
		this.imageUri = originalImageUri;
	
	}
	public String getImageName() {
		return imageName;
	}
	public String getImageUri() {
		return imageUri;
	}
	public String getContentType() {
		return contentType;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	
	

}
