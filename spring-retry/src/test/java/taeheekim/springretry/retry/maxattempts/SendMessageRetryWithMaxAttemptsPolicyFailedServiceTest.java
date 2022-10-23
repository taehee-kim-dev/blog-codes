package taeheekim.springretry.retry.maxattempts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SendMessageRetryWithMaxAttemptsPolicyFailedServiceTest {

    @Autowired
    private SendMessageRetryWithMaxAttemptsPolicyFailedService sendMessageRetryWithMaxAttemptsPolicyFailedService;

    @Test
    void retry() {
        sendMessageRetryWithMaxAttemptsPolicyFailedService.run();
    }
}