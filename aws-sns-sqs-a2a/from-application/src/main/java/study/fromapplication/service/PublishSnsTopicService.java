package study.fromapplication.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;
import study.fromapplication.dto.PublishSnsTopicRequest;

@Slf4j
@Service
public class PublishSnsTopicService {

    public void publish(PublishSnsTopicRequest publishSnsTopicRequest) {
        SnsClient snsClient = getSnsClient();
        try {
            PublishRequest request = getPublishRequestBy(publishSnsTopicRequest);
            PublishResponse result = snsClient.publish(request);
            System.out.println(result.messageId() + " Message sent. Status is " + result.sdkHttpResponse().statusCode());
        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        snsClient.close();
    }

    private SnsClient getSnsClient() {
        return SnsClient.builder()
            .region(Region.AP_NORTHEAST_2)
            .build();
    }

    private PublishRequest getPublishRequestBy(PublishSnsTopicRequest publishSnsTopicRequest) {
        return PublishRequest.builder()
            .message(publishSnsTopicRequest.getMessage())
            .topicArn(publishSnsTopicRequest.getTopicArn())
            .build();
    }
}
