package com.example.shop.flyweight.strategy;

import com.example.shop.flyweight.GenericStrategy;
import com.example.shop.flyweight.domain.FileType;


public interface GeneratorStrategy extends GenericStrategy<FileType> {

    byte[] generateFile();
}
