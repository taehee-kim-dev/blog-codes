package taeheekim.springretry.retry.timeout;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SendMessageRetryWithTimoutPolicySuccessServiceTest {

    @Autowired
    private SendMessageRetryWithTimoutPolicySuccessService sendMessageRetryWithTimoutPolicySuccessService;

    @Test
    void retry() {
        sendMessageRetryWithTimoutPolicySuccessService.run();
    }
}