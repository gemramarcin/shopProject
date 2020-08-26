package com.example.shop.config;

import com.hazelcast.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Bean
    public Config configHazelcast(){
        return new Config()
                .setInstanceName("hazelcast1")
                .addMapConfig(new MapConfig()
                        .setName("product")
                        .setEvictionConfig(new EvictionConfig()
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setSize(1111)
                                .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE))
                        .setTimeToLiveSeconds(60 * 60 * 24));

    }
}
