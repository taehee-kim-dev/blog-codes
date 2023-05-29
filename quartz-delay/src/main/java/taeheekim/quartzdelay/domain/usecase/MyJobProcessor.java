package taeheekim.quartzdelay.domain.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;
import taeheekim.quartzdelay.port.inbound.MyJobStarter;

import java.time.LocalDateTime;

import static org.quartz.TriggerBuilder.newTrigger;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyJobProcessor implements MyJobStarter {

    private final Scheduler scheduler; // Quartz의 스케쥴러를 주입받습니다.

    @Override
    public void startMyJob() {
        // JobDataMap을 통해 실행되는 Job에게 key-value 형식으로 데이터를 전달합니다.
        // JobDataMap은 JobExecutionContext를 통해 전달됩니다.
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("ABCDE", "12345");

        // 실행시킬 Job을 만들고, 위에서 생성한 JobDataMap을 전달합니다.
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class) // 앞서 생성한 MyJob 클래스를 지정합니다.
                .usingJobData(jobDataMap) // JobDataMap를 전달합니다.
                .build();

        // Trigger를 생성합니다.
        Trigger trigger = newTrigger()
                .withIdentity(TriggerKey.triggerKey("myTestJob", "myGroup")) // TriggerKey를 지정합니다.
                .startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND)) // 5초 후에 실행되도록 설정합니다.
                .build();

        try {
            scheduler.scheduleJob(jobDetail, trigger); // JobDetail과 Trigger를 스케쥴러에 등록합니다.
            scheduler.start(); // 스케쥴러를 시작합니다.
        } catch (SchedulerException e) {
            log.error("스케쥴러 실행 실패", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stopMyJob() {
        try {
            scheduler.unscheduleJob(TriggerKey.triggerKey("myTestJob", "myGroup")); // 스케쥴러에서 TriggerKey로 Job을 스케쥴에서 제거합니다.
            log.info("MyJob Stopped time={}", LocalDateTime.now());
        } catch (SchedulerException e) {
            log.error("스케쥴러 중지 실패", e);
            throw new RuntimeException(e);
        }
    }
}
