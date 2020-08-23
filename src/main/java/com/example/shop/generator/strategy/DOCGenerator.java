package com.example.shop.generator.strategy;

import com.example.shop.generator.domain.FileType;
import org.springframework.stereotype.Component;

@Component
public class DOCGenerator extends GeneratorStrategy{
    public DOCGenerator() {
        super(FileType.DOC);
    }

    @Override
    public void generateFile() {
        System.out.println("DOC");
    }
}
