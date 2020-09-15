package com.example.shop.controller;

import com.example.shop.flyweight.GenericFactory;
import com.example.shop.flyweight.domain.FileType;
import com.example.shop.flyweight.strategy.GeneratorStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {
    private final GenericFactory<FileType, GeneratorStrategy> generatorFactory;

    @GetMapping("{fileType}")
    public ResponseEntity<byte[]> generateFile(@PathVariable FileType fileType){
        byte[] file = generatorFactory.getByType(fileType).generateFile();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_LENGTH, Integer.toString(file.length));
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=report." + fileType.name().toLowerCase());

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(file);
    }
}
