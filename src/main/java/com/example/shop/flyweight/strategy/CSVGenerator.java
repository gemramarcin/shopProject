package com.example.shop.flyweight.strategy;


import com.example.shop.generator.domain.FileType;
import org.springframework.stereotype.Component;

@Component
public class CSVGenerator implements GeneratorStrategy {

    @Override
    public FileType getType() {
        return FileType.CSV;
    }

    @Override
    public void generateFile() {
        System.out.println("CSV");
    }
}
