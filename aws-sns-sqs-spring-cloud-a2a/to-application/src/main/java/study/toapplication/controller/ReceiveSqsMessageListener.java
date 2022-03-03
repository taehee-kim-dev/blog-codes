package study.toapplication.controller;

import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReceiveSqsMessageListener {

    @SqsListener(value = "https://sqs.ap-northeast-2.amazonaws.com/385423560848/taeheekim-sqs", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receive(String message) {
        log.info("수신한 메시지 : {}", message);
    }
}
