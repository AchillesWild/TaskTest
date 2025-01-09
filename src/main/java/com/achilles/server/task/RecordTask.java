package com.achilles.server.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@EnableScheduling
public class RecordTask {

    @Autowired
    RestTemplate restTemplate;

    @Scheduled(fixedRate = 7000)
    private void check() {

        log.info("---------------------Start------------------------");

        String result = restTemplate.getForObject("https://quickrecord.cn/record/common/check", String.class);

        log.info("{}", result);

    }
}
