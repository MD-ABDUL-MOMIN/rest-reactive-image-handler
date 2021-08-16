package com.example.responsehelper;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class ResponseObjectForList {
	
   List<ResponseOriginalAndResizedImage> listOfImages;

public List<ResponseOriginalAndResizedImage> getListOfImages() {
	return listOfImages;
}

public void setListOfImages(List<ResponseOriginalAndResizedImage> listOfImages) {
	this.listOfImages = listOfImages;
}
   
   

}
