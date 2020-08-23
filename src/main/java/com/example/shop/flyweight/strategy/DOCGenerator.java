package com.example.shop.flyweight.strategy;

import com.example.shop.generator.domain.FileType;
import org.springframework.stereotype.Component;

@Component
public class DOCGenerator implements GeneratorStrategy {


    @Override
    public void generateFile() {
        System.out.println("DOC");
    }

    @Override
    public FileType getType() {
        return FileType.DOC;
    }
}
