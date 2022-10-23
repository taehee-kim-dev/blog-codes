package taeheekim.springretry.retry.recoverycallback;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SendMessageRetryWithRecoveryCallbackServiceTest {

    @Autowired
    private SendMessageRetryWithRecoveryCallbackService sendMessageRetryWithRecoveryCallbackService;

    @Test
    void retry() {
        sendMessageRetryWithRecoveryCallbackService.run();
    }
}