package data_access;

import entity.User;
import use_case.EventStrategy;

import java.io.IOException;

public class EventProcesser {
    private EventStrategy eventStrategy;

    public EventProcesser(EventStrategy eventStrategy) {
        this.eventStrategy = eventStrategy;
    }

    public Object processEvent(User user) throws IOException {
        return eventStrategy.getEvents(user);
    }

}
