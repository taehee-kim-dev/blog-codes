package taeheekim.springretry.retry.backoff;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SendMessageRetryWithBackoffPolicyServiceTest {

    @Autowired
    private SendMessageRetryWithBackoffPolicyService sendMessageRetryWithBackoffPolicyService;

    @Test
    void retry() {
        sendMessageRetryWithBackoffPolicyService.run();
    }
}