package taeheekim.quartzdelay.domain.usecase;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;

/**
 * Quartz의 Job 인터페이스를 구현한 클래스입니다.
 */
@Slf4j
public class MyJob implements Job {

    /**
     * Job이 실행되면 execute 메서드가 호출됩니다. 스케쥴러의 쓰레드 중 하나에 의해 호출됩니다.
     *
     * @param context JobExecutionContext 객체입니다.
     *                이 Job을 실행하는 런타임 환경에 대한 정보를 담고 있습니다.
     *                Scheduler, Trigger, JobDetail 등을 포함하여 Job 인스턴스에 대한 정보를 제공하는 객체입니다.
     *                여기에서는 JobDataMap에서 key 값을 통해 value값을 가져와 로그로 출력했습니다.
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("MyJob Start time={}", LocalDateTime.now());
        String testValue = context.getJobDetail().getJobDataMap().get("ABCDE").toString();
        log.info("testValue={}", testValue);
        log.info("MyJob End time={}", LocalDateTime.now());
    }
}
