package com.example.shop.generator.strategy;

import com.example.shop.generator.domain.FileType;
import org.springframework.stereotype.Component;

@Component
public class PDFGenerator extends GeneratorStrategy{
    public PDFGenerator() {
        super(FileType.PDF);
    }

    @Override
    public void generateFile() {
        System.out.println("PDF");
    }
}
