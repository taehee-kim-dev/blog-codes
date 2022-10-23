package taeheekim.springretry.retry.timeout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.policy.TimeoutRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
public class SendMessageRetryWithTimoutPolicySuccessService {

    public void run() {
        RetryTemplate retryTemplate = new RetryTemplate();

        TimeoutRetryPolicy timeoutRetryPolicy = new TimeoutRetryPolicy();
        timeoutRetryPolicy.setTimeout(5000L); // 5초 timeout 설정

        retryTemplate.setRetryPolicy(timeoutRetryPolicy); // RetryTemplate에 TimeoutRetryPolicy 세팅

        LocalDateTime startTime = LocalDateTime.now(); // 실행 시작 시간

        try {
            String result = retryTemplate.execute(new RetryCallback<String, Throwable>() {
                @Override
                public String doWithRetry(RetryContext context) throws Throwable {
                    return "메세지 전송에 성공했습니다!";
                }
            });

            log.info("[SendMessageRetryWithTimoutPolicySuccessService][run][SendMessage] result={}", result);
        } catch (Throwable e) {
            log.error("[SendMessageRetryWithTimoutPolicySuccessService][run][SendMessage] failed. {}", e.getMessage(), e);
        } finally {
            long executionDuration = startTime.until(LocalDateTime.now(), ChronoUnit.MILLIS);
            log.info("[SendMessageRetryWithTimoutPolicySuccessService][run][SendMessage] 메세지 전송 총 실행 시간={}ms", executionDuration);
        }
    }
}
