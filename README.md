# rest-reactive-image-handler
Technology:
- java
- spring boot
- mysql


## Endpoint 

- GET Request   For Image list    http://localhost:8081/image/list 
- RESPONSE: 
  <code>
  {
    "listOfImages": [
        {
            "original": {
                "imageName": "jlpt.PNG",
                "imageUri": "http://localhost:8081/image/showoriginal/36",
                "contentType": "image/png"
            },
            "resized": {
                "imageName": "jlpt_200x200.PNG",
                "imageUri": "http://localhost:8081/image/showresized/36",
                "contentType": "image/png"
            }
        }
        }
    ]
}
  </code>
- Get Request: For single image http://localhost:8081/image/showoriginal/36  And http://localhost:8081/image/showresized/36
- Response: Image file

- post request  http://localhost:8081/image/upload
- Response 
- <code>
  {
    "listOfImages": [
        {
            "original": {
                "imageName": "mominup.jpg",
                "imageUri": "http://localhost:8081/image/showoriginal/43",
                "contentType": "image/jpeg"
            },
            "resized": {
                "imageName": "mominup_200x200.jpg",
                "imageUri": "http://localhost:8081/image/showresized/43",
                "contentType": "image/jpeg"
            }
        }
    ]
}
  </code>
























