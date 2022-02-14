package taeheekim.applicationeventpublisher.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MyEventOld extends ApplicationEvent {

    private final String data;

    public MyEventOld(Object source, String data) {
        super(source);
        this.data = data;
    }
}
