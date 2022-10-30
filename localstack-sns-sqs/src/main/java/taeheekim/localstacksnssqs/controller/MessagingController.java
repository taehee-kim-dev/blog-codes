package taeheekim.localstacksnssqs.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import taeheekim.localstacksnssqs.aws.sns.AwsSnsMessagePublisher;
import taeheekim.localstacksnssqs.aws.sqs.AwsSqsMessagePublisher;
import taeheekim.localstacksnssqs.dto.Message;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessagingController {

    private final AwsSnsMessagePublisher awsSnsMessagePublisher;
    private final AwsSqsMessagePublisher awsSqsMessagePublisher;

    @PostMapping("/api/v1/aws/sns/messaging/publish")
    public String publishToSns(@RequestBody Message message) {
        awsSnsMessagePublisher.publish(message);
        return "SNS에 메세지 전송을 완료했습니다.";
    }

    @PostMapping("/api/v1/aws/sqs/messaging/publish")
    public String publishToSqs(@RequestBody Message message) {
        awsSqsMessagePublisher.publish(message);
        return "SQS에 메세지 전송을 완료했습니다.";
    }
}
