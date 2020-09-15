package com.example.shop.flyweight.strategy;

import com.example.shop.flyweight.domain.FileType;
import org.springframework.stereotype.Component;

@Component
public class JSONGenerator implements GeneratorStrategy {

    @Override
    public byte[] generateFile() {
        return new byte[0];
    }

    @Override
    public FileType getType() {
        return FileType.JSON;
    }
}
