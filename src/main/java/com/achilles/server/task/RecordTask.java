package com.achilles.server.task;

import com.achilles.tool.date.DateUtil;
import com.achilles.tool.email.EmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@EnableScheduling
public class RecordTask {

    @Autowired
    RestTemplate restTemplate;

    String host = "smtp.qq.com"; // QQ SMTP服务器地址
    String port = "465";
    String password = "xpvwbfeqmwqodjgd";
    String sender = "2236966280@qq.com";
    String receiver = "AchillesWild@hotmail.com";

    @Scheduled(fixedRate = 3000)
    private void check() {

        String result = null;
        try {
            result = restTemplate.getForObject("https://quickrecord.cn/record/common/check", String.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            String subject = "check_j";
            String text = "something wrong ! (" + DateUtil.getCurrentStr(DateUtil.YYYY_MM_DD_HH_MM_SS_SSS) + ")";
            EmailUtil.send(host, port, sender, password, receiver, subject, text);
        }

        log.info("{}", result);

    }
}
