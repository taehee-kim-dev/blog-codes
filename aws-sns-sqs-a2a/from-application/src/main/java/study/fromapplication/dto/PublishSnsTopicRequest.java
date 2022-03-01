package study.fromapplication.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class PublishSnsTopicRequest {

    private String topicArn;
    private String message;
}
