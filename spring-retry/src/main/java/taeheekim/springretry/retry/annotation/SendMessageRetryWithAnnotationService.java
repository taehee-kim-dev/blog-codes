package taeheekim.springretry.retry.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
public class SendMessageRetryWithAnnotationService {

    private static final boolean OCCUR_EXCEPTION = true;
    private static int ATTEMPTS_COUNT = 0;
    private static final LocalDateTime START_TIME = LocalDateTime.now();

    /**
     * 예외 발생시 재시도할 비즈니스로직 입니다.
     * - IllegalArgumentException이 발생했을 때만 재시도
     * - 최대 시도 횟수 : 5회
     * - 재시도 간격 : 3초
     */
    @Retryable(include = IllegalArgumentException.class, maxAttempts = 5, backoff = @Backoff(delay = 3000))
    public String sendMessage() {
        ATTEMPTS_COUNT += 1;

        log.info("[SendMessageRetryWithAnnotationService][sendMessage][SendMessage] 지금까지 시도 횟수={}회, 처음부터 지금까지 걸린 시간={}ms",
                ATTEMPTS_COUNT, START_TIME.until(LocalDateTime.now(), ChronoUnit.MILLIS));

        if (OCCUR_EXCEPTION) {
            throw new IllegalArgumentException();
        }

        return "메세지 전송에 성공했습니다!";
    }

    /**
     * 최종 실패 조건 도달시, 실행될 RecoveryCallback 함수 입니다.
     */
    @Recover
    public String sendMessageRecovery() {
        log.warn("[SendMessageRetryWithAnnotationService][sendMessageRecovery][SendMessage] 메세지 전송에 실패해, 메세지 전송 복구 로직이 실행되었습니다. 메세지 전송 총 시도 횟수={}회, 처음부터 지금까지 걸린 시간={}ms",
                ATTEMPTS_COUNT, START_TIME.until(LocalDateTime.now(), ChronoUnit.MILLIS));

        return "메세지 전송 복구 로직이 실행되었습니다.";
    }
}
