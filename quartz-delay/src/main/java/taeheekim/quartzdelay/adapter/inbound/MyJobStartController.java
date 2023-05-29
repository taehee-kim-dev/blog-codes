package taeheekim.quartzdelay.adapter.inbound;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import taeheekim.quartzdelay.port.inbound.MyJobStarter;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MyJobStartController {

    private final MyJobStarter myJobStarter;

    @GetMapping("/start-myjob")
    public String startMyJob() {
        log.info("MyJobStartController - startMyJob");
        myJobStarter.startMyJob();
        return "MyJobStarted!!";
    }

    @GetMapping("/stop-myjob")
    public String stopMyJob() {
        log.info("MyJobStartController - stopMyJob");
        myJobStarter.stopMyJob();
        return "MyJobStopped!!";
    }
}
