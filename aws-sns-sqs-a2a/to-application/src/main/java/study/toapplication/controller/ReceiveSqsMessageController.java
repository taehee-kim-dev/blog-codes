package study.toapplication.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.toapplication.service.ReceiveSqsMessagesService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ReceiveSqsMessageController {

    private final ReceiveSqsMessagesService receiveSqsMessagesService;

    @GetMapping("/api/v1/sqs/receive-messages")
    public String receive(String sqsUrl) {
        receiveSqsMessagesService.receiveMessages(sqsUrl);
        return "SQS Receive messages 성공!!";
    }
}
