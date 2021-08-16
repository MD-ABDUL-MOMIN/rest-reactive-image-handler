package com.example.image_handler_package;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageHandlerModel, Long>{
	
	

}
