package taeheekim.applicationeventpublisher.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MyEventNew {

    private final Object source;
    private final String data;

    public MyEventNew(Object source, String data) {
        this.source = source;
        this.data = data;
    }
}
