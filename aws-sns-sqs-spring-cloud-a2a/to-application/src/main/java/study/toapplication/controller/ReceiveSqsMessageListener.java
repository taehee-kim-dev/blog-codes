package study.toapplication.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReceiveSqsMessageListener {

    @SqsListener(value = "taeheekim-sqs", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receive(String message) {
        log.info("수신한 메시지 : {}", message);
    }
}
