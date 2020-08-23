package com.example.shop.generator.strategy;

import com.example.shop.generator.domain.FileType;
import org.springframework.stereotype.Component;

@Component
public class CSVGenerator extends GeneratorStrategy{
    public CSVGenerator() {
        super(FileType.CSV);
    }

    @Override
    public void generateFile() {
        System.out.println("CSV");
    }
}
