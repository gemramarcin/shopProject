package com.example.shop.generator.strategy;

import com.example.shop.generator.domain.FileType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class GeneratorStrategy {
    @Getter
    private final FileType fileType;

    public abstract void generateFile();
}
