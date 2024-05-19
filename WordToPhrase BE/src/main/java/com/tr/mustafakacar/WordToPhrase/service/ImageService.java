package com.tr.mustafakacar.WordToPhrase.service;

import com.tr.mustafakacar.WordToPhrase.entity.ImageEntity;
import com.tr.mustafakacar.WordToPhrase.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ImageEntity saveImage(MultipartFile multipartFile) throws IOException {
        ImageEntity image = ImageEntity.builder()
                .name(multipartFile.getOriginalFilename())
                .type(multipartFile.getContentType())
                .file(multipartFile.getBytes()).build();

        return imageRepository.save(image);
    }



public byte[] getOneImage(String name) {
    ImageEntity image=imageRepository.findByName(name).orElseThrow(()->new RuntimeException("Image not found"));

    return image.getFile();
}

}
