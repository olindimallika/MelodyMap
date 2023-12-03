package data_access;

import entity.User;
import use_case.EventStrategy;

import java.io.IOException;

public class EventProcesser<T> {
    private final EventStrategy<T> eventStrategy;

    public EventProcesser(EventStrategy<T> eventStrategy) {
        this.eventStrategy = eventStrategy;
    }

    public T processEvent(User user) throws IOException {
        return eventStrategy.getEvents(user);
    }

}
