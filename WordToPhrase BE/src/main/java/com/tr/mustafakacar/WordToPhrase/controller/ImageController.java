package com.tr.mustafakacar.WordToPhrase.controller;

import com.tr.mustafakacar.WordToPhrase.service.ImageService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> saveImage(@RequestParam("image") MultipartFile file) {
        try {
            imageService.saveImage(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(file.getOriginalFilename());
    }
    @GetMapping("/{name}")
    public HttpEntity<byte[]> getImage(@PathVariable String name){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageService.getOneImage(name), httpHeaders, HttpStatus.OK);
    }
}
