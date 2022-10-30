package taeheekim.localstacksnssqs.aws.sqs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;
import taeheekim.localstacksnssqs.dto.Message;

@Slf4j
@Component
public class AwsSqsMessageListener {

    @SqsListener(value = "${aws.sqs.messaging.from-SNS}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void listenFromSnsMessage(@NotificationMessage Message message) {
        log.info("SNS 발행하고, SQS에서 수신한 메세지={}", message);
    }

    @SqsListener(value = "${aws.sqs.messaging.only-SQS}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void listenOnlyMessage(Message message) {
        log.info("SQS에서 수신한 메세지={}", message);
    }
}
