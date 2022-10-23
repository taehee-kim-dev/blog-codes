package taeheekim.springretry.retry.fatalexceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SendMessageRetryWithFatalExceptionPolicyService {

    private static final boolean OCCUR_EXCEPTION = true;
    private static int ATTEMPTS_COUNT = 0;

    public void run() {
        RetryTemplate retryTemplate = RetryTemplate.builder()
                .maxAttempts(5)
                .retryOn(IllegalArgumentException.class) // IllegalArgumentException 발생시에는 재시도
                .build();

        try {
            String result = retryTemplate.execute(new RetryCallback<String, Throwable>() {
                @Override
                public String doWithRetry(RetryContext context) throws Throwable {
                    ATTEMPTS_COUNT += 1;

                    if (OCCUR_EXCEPTION) {
                        throw new IllegalStateException(); // 발생 예외 수정
                    }

                    return "메세지 전송에 성공했습니다!";
                }
            });

            log.info("[SendMessageRetryWithFatalExceptionPolicyService][run][SendMessage] result={}", result);
        } catch (Throwable e) {
            log.error("[SendMessageRetryWithFatalExceptionPolicyService][run][SendMessage] failed. {}", e.getMessage(), e);
        } finally {
            log.info("[SendMessageRetryWithFatalExceptionPolicyService][run][SendMessage] 메세지 전송 총 시도 횟수={}회", ATTEMPTS_COUNT);
        }
    }
}
