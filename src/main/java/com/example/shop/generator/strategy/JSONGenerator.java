package com.example.shop.generator.strategy;

import com.example.shop.generator.domain.FileType;
import org.springframework.stereotype.Component;

@Component
public class JSONGenerator extends GeneratorStrategy{
    public JSONGenerator() {
        super(FileType.JSON);
    }

    @Override
    public void generateFile() {
        System.out.println("JSON");
    }
}
