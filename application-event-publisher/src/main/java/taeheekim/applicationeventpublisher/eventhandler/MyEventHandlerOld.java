package taeheekim.applicationeventpublisher.eventhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import taeheekim.applicationeventpublisher.event.MyEventOld;

@Slf4j
@Component
public class MyEventHandlerOld implements ApplicationListener<MyEventOld> {

    @Override
    public void onApplicationEvent(MyEventOld myEventOld) {
        log.info("MyEventOld 이벤트 전달받음. data = {}", myEventOld.getData());
    }
}
