package com.example.responsehelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class ResponseOriginalAndResizedImage {
	@Autowired
	private ImageResponse original;

	@Autowired
	private ImageResponse resized;

	public ResponseOriginalAndResizedImage() {
		// TODO Auto-generated constructor stub
	}

	public ImageResponse getOriginal() {
		return original;
	}

	public ImageResponse getResized() {
		return resized;
	}

	public void setOriginal(ImageResponse original) {
		this.original = original;
	}

	public void setResized(ImageResponse resized) {
		this.resized = resized;
	}

	public ResponseOriginalAndResizedImage(ImageResponse original, ImageResponse resized) {

		this.original = original;
		this.resized = resized;
	}

}
