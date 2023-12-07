package data_access;
import entity.*;
import java.io.IOException;

/**
 * The EventProcessor class is responsible for processing events based on a specified strategy.
 * It uses an implementation of the EventStrategy interface to retrieve events for a given user.
 *
 * @param <T> The type of data representing events, either a list of JSON objects, or a list of
 *           lists of JSON objects.
 */

public class EventProcesser<T> {
    private final EventStrategy<T> eventStrategy;

    /**
     * Constructs an EventProcessor with a specified EventStrategy.
     *
     * @param eventStrategy The strategy used for processing events.
     */

    public EventProcesser(EventStrategy<T> eventStrategy) {
        this.eventStrategy = eventStrategy;
    }

    /**
     * Processes events for a given user using the specified EventStrategy.
     *
     * @param user The user for whom events are processed.
     * @return The processed events of type T.
     * @throws IOException If an I/O error occurs during event processing.
     */

    public T processEvent(User user) throws IOException {
        return eventStrategy.getEvents(user);
    }
}
