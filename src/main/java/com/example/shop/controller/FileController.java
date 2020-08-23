package com.example.shop.controller;

import com.example.shop.generator.GeneratorFactory;
import com.example.shop.generator.domain.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {
    private final GeneratorFactory generatorFactory;

    @GetMapping("{fileType}")
    public void generateFile(@PathVariable FileType fileType){
        generatorFactory.getByFileType(fileType).generateFile();
    }
}
