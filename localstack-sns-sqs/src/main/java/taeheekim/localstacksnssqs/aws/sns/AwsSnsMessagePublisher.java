package taeheekim.localstacksnssqs.aws.sns;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import taeheekim.localstacksnssqs.dto.Message;

@Slf4j
@Service
@RequiredArgsConstructor
public class AwsSnsMessagePublisher implements InitializingBean {

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @Value("${aws.sns.messaging}")
    private String destination;

    public void publish(Message message) {
        log.info("AWS SNS에 메세지를 전송합니다. destination={}, payload={}", destination, message);
        notificationMessagingTemplate.sendNotification(destination, message, null);
        log.info("AWS SNS에 메세지 전송을 완료했습니다. destination={}, payload={}", destination, message);
    }

    @Override
    public void afterPropertiesSet() {
        Assert.hasText(destination, "AwsSnsMessagePublisher의 destination 값은 빈 문자열일 수 없습니다.");
        log.info("AwsSnsMessagePublisher destination={}", destination);
    }
}
