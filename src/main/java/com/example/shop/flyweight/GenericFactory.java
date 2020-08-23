package com.example.shop.flyweight;

import com.example.shop.generator.domain.FileType;
import com.example.shop.generator.strategy.GeneratorStrategy;
import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GenericFactory<K, V extends GenericStrategy<K>> {
    private final List<V> strategies;
    private Map<K, V> strategyMap;

    @PostConstruct
    public void init(){
        strategyMap = strategies.stream()
                .collect(Collectors.toMap(GenericStrategy::getType, Function.identity()));
    }

    public V getByFileType(K k){
        return strategyMap.get(k);
    }

}
