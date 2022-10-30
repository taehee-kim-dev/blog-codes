package taeheekim.localstacksnssqs.aws.sqs;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import taeheekim.localstacksnssqs.dto.Message;

@Slf4j
@Service
@RequiredArgsConstructor
public class AwsSqsMessagePublisher implements InitializingBean {

    private final QueueMessagingTemplate queueMessagingTemplate;
    private final ObjectMapper objectMapper;

    @Value("${aws.sqs.messaging.only-SQS}")
    private String destination;

    public void publish(Message message) {
        log.info("AWS SQS에 메세지를 전송합니다. destination={}, payload={}", destination, message);
        String payload = getPayload(message);
        queueMessagingTemplate.send(destination, MessageBuilder.withPayload(payload).build());
        log.info("AWS SQS에 메세지 전송을 완료했습니다. destination={}, payload={}", destination, message);
    }

    private String getPayload(Message message) {
        try {
            return objectMapper.writeValueAsString(message);
        } catch (Exception e) {
            log.error("SQS 메세지 전송에 실패했습니다. message={}", message, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void afterPropertiesSet() {
        Assert.hasText(destination, "AwsSqsMessagePublisher의 destination 값은 빈 문자열일 수 없습니다.");
        log.info("AwsSqsMessagePublisher destination={}", destination);
    }
}
