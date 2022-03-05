package study.fromapplication.service;

import com.amazonaws.services.sns.AmazonSNS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Service;
import study.fromapplication.dto.PublishSnsTopicRequest;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PublishSnsTopicService {

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    public PublishSnsTopicService(AmazonSNS amazonSNS) {
        notificationMessagingTemplate = new NotificationMessagingTemplate(amazonSNS);
    }

    public void publish(PublishSnsTopicRequest publishSnsTopicRequest) {
        Map<String, Object> headers = new HashMap<>();
        if (publishSnsTopicRequest.getFifo()) {
            headers.put("messageGroupId", publishSnsTopicRequest.getMessageGroupId());
            headers.put("deduplicationId", publishSnsTopicRequest.getMessageDeduplicationId());
        }
        notificationMessagingTemplate.convertAndSend(
                publishSnsTopicRequest.getTopicArn(),
                publishSnsTopicRequest.getMessage(),
                headers
        );

        log.info("메세지 전송됨. message = {}", publishSnsTopicRequest.getMessage());
    }
}
