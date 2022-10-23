package taeheekim.springretry.retry.fatalexceptions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SendMessageRetryWithFatalExceptionPolicyServiceTest {

    @Autowired
    private SendMessageRetryWithFatalExceptionPolicyService sendMessageRetryWithFatalExceptionPolicyService;

    @Test
    void retry() {
        sendMessageRetryWithFatalExceptionPolicyService.run();
    }
}