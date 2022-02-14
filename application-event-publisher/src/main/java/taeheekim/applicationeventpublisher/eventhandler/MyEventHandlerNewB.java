package taeheekim.applicationeventpublisher.eventhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import taeheekim.applicationeventpublisher.event.MyEventNew;

@Slf4j
@Component
public class MyEventHandlerNewB {

    @Async
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @EventListener
    public void handle(MyEventNew myEventNew) {
        log.info("Thread name = {}", Thread.currentThread().getName());
        log.info("MyEventHandlerNewB에서 MyEventNew 이벤트 전달받음. data = {}", myEventNew.getData());
    }
}
