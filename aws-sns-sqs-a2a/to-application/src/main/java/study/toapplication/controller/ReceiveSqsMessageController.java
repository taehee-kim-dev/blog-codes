package study.toapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.toapplication.dto.ReceiveSqsMessagesRequest;
import study.toapplication.service.ReceiveSqsMessagesService;

@RequiredArgsConstructor
@RestController
public class ReceiveSqsMessageController {

    private ReceiveSqsMessagesService receiveSqsMessagesService;

    @GetMapping("/api/v1/sqs/receive-messages")
    public String receive(@RequestBody ReceiveSqsMessagesRequest receiveSqsMessagesRequest) {
        receiveSqsMessagesService.receiveMessages(receiveSqsMessagesRequest);
        return "SQS Receive messages 성공!!";
    }
}
