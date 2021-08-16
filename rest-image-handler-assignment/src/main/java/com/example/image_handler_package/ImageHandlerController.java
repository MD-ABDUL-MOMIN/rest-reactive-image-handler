package com.example.image_handler_package;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.responsehelper.ResponseObjectForList;
import com.example.responsehelper.ResponseOriginalAndResizedImage;

import javassist.NotFoundException;

@RestController
@RequestMapping("image")
public class ImageHandlerController {

	private static final Logger logger = LoggerFactory.getLogger(ImageHandlerController.class);

	@Autowired
	private ImageStorageHandlerService imageStorageService;

	public ResponseOriginalAndResizedImage uploadFile(MultipartFile file) {

		try {
			ImageHandlerModel imageFile = imageStorageService.storeOriginalImageData(file);

			ImageHandlerModel resizedImage = imageStorageService.resizedImageBy200x200(imageFile);

			return imageStorageService.makeResponseOriginalAndResizedFormat(imageFile, resizedImage);

		} catch (Exception e) {
			return null;
		}

	}

	@PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseObjectForList uploadMultipleFiles(@RequestParam("images") MultipartFile[] files) {
		ResponseObjectForList responseObject = new ResponseObjectForList();

		List<ResponseOriginalAndResizedImage> listOfImages = Arrays.asList(files).stream().map(file -> uploadFile(file))
				.collect(Collectors.toList());
		responseObject.setListOfImages(listOfImages);

		return responseObject;
	}

	@GetMapping(value = "/showoriginal/{fileId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ByteArrayResource> getOriginalImageFile(@PathVariable String fileId)
			throws NotFoundException {
		// Load file from database
		ImageHandlerModel imageData = imageStorageService.getImageFile(Long.valueOf(fileId));

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(imageData.getContentType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + imageData.getOriginalImageName() + "\"")
				.body(new ByteArrayResource(imageData.getOriginalImageData()));
	}

	@GetMapping(value = "/showresized/{fileId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ByteArrayResource> getResizedImageFile(@PathVariable String fileId)
			throws NotFoundException, IOException {

		ImageHandlerModel imageData = imageStorageService.getImageFile(Long.valueOf(fileId));

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(imageData.getContentType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + imageData.getResizedImageName() + "\"")
				.body(new ByteArrayResource(imageData.getResizedImageData()));

	}

	@GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseObjectForList getImageList() {

		List<ResponseOriginalAndResizedImage> listOfImages = imageStorageService.findImageList();
		ResponseObjectForList responseObject = new ResponseObjectForList();
		responseObject.setListOfImages(listOfImages);

		return responseObject;
	}

}
