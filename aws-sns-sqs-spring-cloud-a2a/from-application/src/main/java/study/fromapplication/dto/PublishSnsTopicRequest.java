package study.fromapplication.dto;

import lombok.Getter;

@Getter
public class PublishSnsTopicRequest {

    private String topicArn;
    private String message;
    private Boolean fifo;
    private String messageGroupId;
    private String messageDeduplicationId;

    @Override
    public String toString() {
        return "PublishSnsTopicRequest{" +
                "topicArn='" + topicArn + '\'' +
                ", message='" + message + '\'' +
                ", fifo=" + fifo +
                ", messageGroupId='" + messageGroupId + '\'' +
                ", messageDeduplicationId='" + messageDeduplicationId + '\'' +
                '}';
    }
}
