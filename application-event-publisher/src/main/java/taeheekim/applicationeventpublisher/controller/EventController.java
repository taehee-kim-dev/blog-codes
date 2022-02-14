package taeheekim.applicationeventpublisher.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import taeheekim.applicationeventpublisher.event.MyEventNew;
import taeheekim.applicationeventpublisher.event.MyEventOld;

@Slf4j
@RequiredArgsConstructor
@ResponseBody
@Controller
public class EventController {

    private final ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/old/api/publish-event")
    public String publishMyEventOld(String data) {
        MyEventOld myEventOld = new MyEventOld(this, data);
        log.info("Thread name = {}", Thread.currentThread().getName());
        log.info("MyEventOld 이벤트 발생시킴. data = {}", data);
        applicationEventPublisher.publishEvent(myEventOld);
        return data;
    }

    @GetMapping("/new/api/publish-event")
    public String publishMyEventNew(String data) {
        MyEventNew myEventNew = new MyEventNew(this, data);
        log.info("Thread name = {}", Thread.currentThread().getName());
        log.info("myEventNew 이벤트 발생시킴. data = {}", data);
        applicationEventPublisher.publishEvent(myEventNew);
        return data;
    }
}
