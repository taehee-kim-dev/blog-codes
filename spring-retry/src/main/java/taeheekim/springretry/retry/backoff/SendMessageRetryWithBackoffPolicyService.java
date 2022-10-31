package taeheekim.springretry.retry.backoff;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
public class SendMessageRetryWithBackoffPolicyService {

    private static final boolean OCCUR_EXCEPTION = true;
    private static int ATTEMPTS_COUNT = 0;

    public void run() {
        RetryTemplate retryTemplate = RetryTemplate.builder()
                .maxAttempts(5) // 최대 시도 횟수 5회로 설정
                .fixedBackoff(3000)
                .build();

        LocalDateTime startTime = LocalDateTime.now(); // 실행 시작 시간

        try {
            String result = retryTemplate.execute(new RetryCallback<String, Throwable>() {
                public String doWithRetry(RetryContext context) {

                    ATTEMPTS_COUNT += 1;

                    long currentExecutionDuration = startTime.until(LocalDateTime.now(), ChronoUnit.MILLIS); // 처음부터 지금까지 걸린 시간 측정
                    log.info("[SendMessageRetryWithBackoffPolicyService][run][SendMessage] 처음부터 지금까지 걸린 시간={}ms", currentExecutionDuration);

                    if (OCCUR_EXCEPTION) {
                        throw new IllegalArgumentException();
                    }

                    return "메세지 전송에 성공했습니다!";
                }
            }, new RecoveryCallback<String>() {
                @Override
                public String recover(RetryContext context) throws Exception {
                    log.warn("[SendMessageRetryWithBackoffPolicyService][run][SendMessage] 메세지 전송에 실패해, 메세지 전송 복구 로직이 실행되었습니다. 메세지 전송 총 시도 횟수={}회",
                            context.getRetryCount());

                    return "메세지 전송 복구 로직이 실행되었습니다.";
                }
            });

            log.info("[SendMessageRetryWithBackoffPolicyService][run][SendMessage] result={}", result);
        } catch (Throwable e) {
            log.error("[SendMessageRetryWithBackoffPolicyService][run][SendMessage] failed. {}", e.getMessage(), e);
        } finally {
            long executionDuration = startTime.until(LocalDateTime.now(), ChronoUnit.MILLIS);
            log.info("[SendMessageRetryWithBackoffPolicyService][run][SendMessage] 메세지 전송 총 시도 횟수={}회, 총 실행 시간={}ms", ATTEMPTS_COUNT, executionDuration);
        }
    }
}
