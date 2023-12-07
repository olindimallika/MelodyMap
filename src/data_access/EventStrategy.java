package data_access;

import entity.User;
import java.io.IOException;

/**
 * The EventStrategy interface defines a strategy for obtaining events based on a specified user.
 * @param <T> The type of data representing events, either a list of JSON objects, or a list of
 *           lists of JSON objects.
 */

public interface EventStrategy<T> {
    T getEvents(User user) throws IOException;
}