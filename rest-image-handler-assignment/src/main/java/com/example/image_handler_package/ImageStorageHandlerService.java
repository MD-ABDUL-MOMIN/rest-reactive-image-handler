package com.example.image_handler_package;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.image_handler_package.exception.FileStorageException;
import com.example.responsehelper.ImageResponse;
import com.example.responsehelper.ResponseOriginalAndResizedImage;

import javassist.NotFoundException;
import net.coobird.thumbnailator.Thumbnails;

@Service
public class ImageStorageHandlerService {

	

	@Autowired
	private ImageRepository imageRepository;

	public ImageHandlerModel storeOriginalImageData(MultipartFile file) throws IOException, FileStorageException {
	
		
		ImageHandlerModel imageModel = new ImageHandlerModel();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		
		try {
			if(fileName.contains("..")||fileName.contains(" ")) {
			   	throw new FileStorageException("filename not found");
			}
		
		imageModel.setOriginalImageData(file.getBytes());
		imageModel.setOriginalImageName(file.getOriginalFilename());
		imageModel.setContentType(file.getContentType());		
		imageModel.setOriginalImageName(fileName);
		
		imageModel.setResizedImageName(fileName.substring(0,fileName.indexOf("."))+"_200x200"+fileName.substring(fileName.indexOf(".")));
		
		return imageRepository.save(imageModel);

					
		} catch (IOException e) {
			throw new IOException("could not save uploaded file " + fileName);
		}
		}

	  public ImageHandlerModel getImageFile(long imageId) throws NotFoundException {
	        return imageRepository.findById(imageId)
	                .orElseThrow(() -> new NotFoundException("not found image file"));
	    }

	  
	public ImageHandlerModel resizedImageBy200x200(ImageHandlerModel imageFile) throws IOException {
		
		System.out.println(imageFile.getId()+" "+imageFile.getResizedImageName());
		ImageHandlerModel imModel = imageRepository.getById(imageFile.getId());
		
		
		
		ByteArrayInputStream imageStream = new ByteArrayInputStream(imageFile.getOriginalImageData());
	      
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		Thumbnails.of(ImageIO.read(imageStream))
		.size(200, 200).outputFormat("JPEG")
        .outputQuality(1)
		
		.toOutputStream(outputStream);
		
		
		imModel.setResizedImageData(outputStream.toByteArray());
		System.out.println("set resized iamge data");
		imageRepository.save(imModel);
		
		
		return imModel;
		
	
	}

	public String buildImageUri(String string, long id) {
		
		return ServletUriComponentsBuilder.fromCurrentContextPath()
        .path(string)
        .path(String.valueOf(id))
        .toUriString();
		
	}

	public List<ResponseOriginalAndResizedImage> findImageList() {
		
		List<ImageHandlerModel> loadImageFromDb = imageRepository.findAll();
		List<ResponseOriginalAndResizedImage>  listOfImage = new ArrayList<ResponseOriginalAndResizedImage>();
		
		for(ImageHandlerModel imModel: loadImageFromDb) {
			
			ResponseOriginalAndResizedImage rMOR = makeResponseOriginalAndResizedFormat(imModel);
			listOfImage.add(rMOR);
			
		}
		
		return listOfImage;
		
		
	}

	public ResponseOriginalAndResizedImage makeResponseOriginalAndResizedFormat(ImageHandlerModel imageFile,
			ImageHandlerModel resizedImage) {
		
		String originalImageUri = buildImageUri("/image/showoriginal/", imageFile.getId());
		String resizedImageUri = buildImageUri("/image/showresized/", imageFile.getId());

		return new ResponseOriginalAndResizedImage(
				new ImageResponse(imageFile.getOriginalImageName(), originalImageUri, imageFile.getContentType()),
				new ImageResponse(resizedImage.getResizedImageName(), resizedImageUri, imageFile.getContentType()));
	}
	
	public ResponseOriginalAndResizedImage makeResponseOriginalAndResizedFormat(ImageHandlerModel imageFile) {
		
		String originalImageUri = buildImageUri("/image/showoriginal/", imageFile.getId());
		String resizedImageUri = buildImageUri("/image/showresized/", imageFile.getId());

		return new ResponseOriginalAndResizedImage(
				new ImageResponse(imageFile.getOriginalImageName(), originalImageUri, imageFile.getContentType()),
				new ImageResponse(imageFile.getResizedImageName(), resizedImageUri, imageFile.getContentType()));
	}
	


}
