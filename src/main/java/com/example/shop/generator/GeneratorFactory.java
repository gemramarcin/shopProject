package com.example.shop.generator;

import com.example.shop.generator.domain.FileType;
import com.example.shop.generator.strategy.GeneratorStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class GeneratorFactory {

    private final List<GeneratorStrategy> generatorStrategies;
    private Map<FileType, GeneratorStrategy> strategyMap;

    @PostConstruct
    public void init(){
        strategyMap = generatorStrategies.stream()
                .collect(Collectors.toMap(GeneratorStrategy::getFileType, Function.identity()));
    }

    public GeneratorStrategy getByFileType(FileType fileType){
        return strategyMap.get(fileType);
    }
}
