package taeheekim.springretry.retry.annotation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SendMessageRetryWithAnnotationServiceTest {

    @Autowired
    private SendMessageRetryWithAnnotationService sendMessageRetryWithAnnotationService;

    @Test
    void retryAnnotationTest() {
        sendMessageRetryWithAnnotationService.sendMessage();
    }
}