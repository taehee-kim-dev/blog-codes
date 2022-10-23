package taeheekim.springretry.retry.maxattempts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SendMessageRetryWithMaxAttemptsPolicySuccessServiceTest {

    @Autowired
    private SendMessageRetryWithMaxAttemptsPolicySuccessService sendMessageRetryWithMaxAttemptsPolicySuccessService;

    @Test
    void retry() {
        sendMessageRetryWithMaxAttemptsPolicySuccessService.run();
    }
}