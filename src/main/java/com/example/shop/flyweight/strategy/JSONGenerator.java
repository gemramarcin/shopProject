package com.example.shop.flyweight.strategy;

import com.example.shop.generator.domain.FileType;
import org.springframework.stereotype.Component;

@Component
public class JSONGenerator implements GeneratorStrategy {

    @Override
    public void generateFile() {
        System.out.println("JSON");
    }

    @Override
    public FileType getType() {
        return FileType.JSON;
    }
}
