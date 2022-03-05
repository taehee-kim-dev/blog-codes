package study.fromapplication.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.AmazonSNSException;
import com.amazonaws.services.sns.model.PublishRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.fromapplication.dto.PublishSnsTopicRequest;

@Slf4j
@Service
public class PublishSnsTopicService {

    public void publish(PublishSnsTopicRequest publishSnsTopicRequest) {
        AmazonSNS amazonSNS = getAmazonSns();
        try {
            PublishRequest request = getPublishRequestBy(publishSnsTopicRequest);
            amazonSNS.publish(request);
        } catch (AmazonSNSException e) {
            log.error(e.getMessage());
            System.exit(1);
        }
        log.info("메세지 전송됨. message = {}", publishSnsTopicRequest.getMessage());
    }

    public AmazonSNS getAmazonSns() {
        return AmazonSNSClientBuilder
            .standard()
            .withRegion(Regions.AP_NORTHEAST_2)
            .build();
    }

    private PublishRequest getPublishRequestBy(PublishSnsTopicRequest publishSnsTopicRequest) {
        log.info("publishSnsTopicRequest = {}", publishSnsTopicRequest);
        if (publishSnsTopicRequest.getFifo()) {
            return new PublishRequest()
                    .withTopicArn(publishSnsTopicRequest.getTopicArn())
                    .withMessage(publishSnsTopicRequest.getMessage())
                    .withMessageGroupId(publishSnsTopicRequest.getMessageGroupId())
                    .withMessageDeduplicationId(publishSnsTopicRequest.getMessageDeduplicationId());

        }
        return new PublishRequest()
            .withTopicArn(publishSnsTopicRequest.getTopicArn())
            .withMessage(publishSnsTopicRequest.getMessage());
    }
}
