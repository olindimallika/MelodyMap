package use_case;

import entity.User;

import java.io.IOException;


public interface EventStrategy<T> {
    T getEvents(User user) throws IOException;
}
