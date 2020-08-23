package com.example.shop.flyweight.strategy;

import com.example.shop.flyweight.GenericStrategy;
import com.example.shop.generator.domain.FileType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


public interface GeneratorStrategy extends GenericStrategy<FileType> {

    void generateFile();
}
