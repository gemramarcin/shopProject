package com.example.shop.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestScheduler {

  //  @Scheduled(fixedRate = 1000)
    public void test() {
        log.info("test");
    }
}
