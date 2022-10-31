package taeheekim.springretry.retry.recoverycallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SendMessageRetryWithRetryContextService {

    private static final boolean OCCUR_EXCEPTION = true;
    private static int ATTEMPTS_COUNT = 0;

    public void run() {
        RetryTemplate retryTemplate = new RetryTemplate();

        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(5); // 최대 시도 횟수 5회로 설정

        retryTemplate.setRetryPolicy(simpleRetryPolicy); // RetryTemplate에 SimpleRetryPolicy 세팅

        try {
            String result = retryTemplate.execute(new RetryCallback<String, Throwable>() {
                public String doWithRetry(RetryContext context) {

                    // RetryContext로 Context 공유
                    Object attemptsCountContext = context.getAttribute("attemptsCount");
                    if (attemptsCountContext == null) {
                        context.setAttribute("attemptsCount", 1);
                    } else {
                        int attemptsCount = (int) attemptsCountContext;
                        context.setAttribute("attemptsCount", attemptsCount + 1);
                    }

                    ATTEMPTS_COUNT += 1;

                    if (OCCUR_EXCEPTION) {
                        throw new IllegalArgumentException();
                    }

                    return "메세지 전송에 성공했습니다!";
                }
            }, new RecoveryCallback<String>() {
                @Override
                public String recover(RetryContext context) throws Exception {
                    log.warn("[SendMessageRetryWithRecoveryCallbackWithRetryContextService][run][SendMessage] 메세지 전송에 실패해, 메세지 전송 복구 로직이 실행되었습니다. 메세지 전송 총 시도 횟수={}회", ATTEMPTS_COUNT);

                    return "메세지 전송 복구 로직이 실행되었습니다.";
                }
            });

            log.info("[SendMessageRetryWithRecoveryCallbackWithRetryContextService][run][SendMessage] result={}", result);
        } catch (Throwable e) {
            log.error("[SendMessageRetryWithRecoveryCallbackWithRetryContextService][run][SendMessage] failed. {}", e.getMessage(), e);
        } finally {
            log.info("[SendMessageRetryWithRecoveryCallbackWithRetryContextService][run][SendMessage] 메세지 전송 총 시도 횟수={}회", ATTEMPTS_COUNT);
        }
    }
}
