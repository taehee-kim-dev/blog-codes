package taeheekim.springretry.retry.recoverycallback;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SendMessageRetryWithRetryContextServiceTest {

    @Autowired
    private SendMessageRetryWithRetryContextService sendMessageRetryWithRetryContextService;

    @Test
    void retry() {
        sendMessageRetryWithRetryContextService.run();
    }
}