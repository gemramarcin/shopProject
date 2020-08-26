package com.example.shop.flyweight.strategy;

import com.example.shop.generator.domain.FileType;
import org.springframework.stereotype.Component;

@Component("XLSGeneratorF")
public class XLSGenerator implements GeneratorStrategy {

    @Override
    public void generateFile() {
        System.out.println("XLS");
    }

    @Override
    public FileType getType() {
        return FileType.XLS;
    }
}
