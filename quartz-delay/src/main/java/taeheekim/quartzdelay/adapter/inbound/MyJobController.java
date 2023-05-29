package taeheekim.quartzdelay.adapter.inbound;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import taeheekim.quartzdelay.port.inbound.MyJobAccessor;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MyJobController {

    private final MyJobAccessor myJobAccessor;

    @GetMapping("/start-myjob")
    public String startMyJob() {
        log.info("MyJobStartController - startMyJob");
        myJobAccessor.startMyJob();
        return "MyJobStarted!!";
    }

    @GetMapping("/stop-myjob")
    public String stopMyJob() {
        log.info("MyJobStartController - stopMyJob");
        myJobAccessor.stopMyJob();
        return "MyJobStopped!!";
    }
}
