package taeheekim.springretry.retry.maxattempts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SendMessageRetryWithMaxAttemptsPolicySuccessService {

    private static int ATTEMPTS_COUNT = 0; // 비즈니스 로직 실행 횟수 측정용 변수 추가

    public void run() {
        RetryTemplate retryTemplate = new RetryTemplate();

        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(5); // 최대 재시도 횟수 5회로 설정

        retryTemplate.setRetryPolicy(simpleRetryPolicy); // RetryTemplate에 SimpleRetryPolicy 세팅

        try {
            String result = retryTemplate.execute(new RetryCallback<String, Throwable>() {
                @Override
                public String doWithRetry(RetryContext context) throws Throwable {

                    ATTEMPTS_COUNT += 1;

                    return "메세지 전송에 성공했습니다!";
                }
            });

            log.info("[SendMessageRetryWithMaxAttemptsPolicySuccessService][run][SendMessage] result={}", result);
        } catch (Throwable e) {
            log.error("[SendMessageRetryWithMaxAttemptsPolicySuccessService][run][SendMessage] failed. {}", e.getMessage(), e);
        } finally {
            log.info("[SendMessageRetryWithMaxAttemptsPolicySuccessService][run][SendMessage] 메세지 전송 총 시도 횟수={}회", ATTEMPTS_COUNT);
        }
    }
}
