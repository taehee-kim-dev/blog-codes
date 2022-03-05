package study.fromapplication.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.AmazonSNSException;
import com.amazonaws.services.sns.model.PublishRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.messaging.Message;
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
            headers.put("message-group-id", publishSnsTopicRequest.getMessageGroupId());
            headers.put("message-deduplication-id", publishSnsTopicRequest.getMessageDeduplicationId());
        }
        notificationMessagingTemplate.convertAndSend(
                publishSnsTopicRequest.getTopicArn(),
                publishSnsTopicRequest.getMessage(),
                headers
        );

        log.info("메세지 전송됨. message = {}", publishSnsTopicRequest.getMessage());
    }
}
