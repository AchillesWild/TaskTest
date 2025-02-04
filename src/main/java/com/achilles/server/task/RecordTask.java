package com.achilles.server.task;

import com.achilles.tool.date.DateUtil;
import com.achilles.tool.email.EmailUtil;
import com.achilles.tool.generate.unique.GenerateRandomString;
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

    long lastSendTime = 0;

    @Scheduled(fixedRate = 3333)
    private void check() {

        String result;
        int length;
        try {
            result = restTemplate.getForObject("https://quickrecord.cn/record/common/check", String.class);
            length = result.length();
        } catch (RestClientException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            long during = (System.currentTimeMillis() - lastSendTime) / 1000;
            if (during <= 60) {
                return;
            }
            lastSendTime = System.currentTimeMillis();
            String subject = "check_j";
            String str = GenerateRandomString.getRandomStr(9192);
            String text = DateUtil.getCurrentStr(DateUtil.YYYY_MM_DD_HH_MM_SS_SSS) + " : " + str;
            EmailUtil.send(host, port, sender, password, receiver, subject, text);
            return;
        }
        log.info("length : {}", length);
        log.info("{}", result);

    }
}
