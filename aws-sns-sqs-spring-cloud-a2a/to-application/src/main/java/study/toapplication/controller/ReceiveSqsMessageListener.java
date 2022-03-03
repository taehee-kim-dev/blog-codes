package study.toapplication.controller;

import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ReceiveSqsMessageListener {

    @SqsListener("taeheekim-sqs.fifo")
    public void receive(String message) {
        log.info("수신한 메시지 : {}", message);
    }
}
