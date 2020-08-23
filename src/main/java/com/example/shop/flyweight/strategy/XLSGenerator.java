package com.example.shop.flyweight.strategy;

import com.example.shop.generator.domain.FileType;
import org.springframework.stereotype.Component;

@Component
public class XLSGenerator extends GeneratorStrategy {
    public XLSGenerator() {
        super(FileType.XLS);
    }

    @Override
    public void generateFile() {
        System.out.println("XLS");
    }
}
