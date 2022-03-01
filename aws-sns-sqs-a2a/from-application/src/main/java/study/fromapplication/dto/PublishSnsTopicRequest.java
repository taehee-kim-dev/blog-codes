package study.fromapplication.dto;

import lombok.Getter;

@Getter
public class PublishSnsTopicRequest {

    private String topicArn;
    private String message;
}
