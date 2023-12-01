package use_case;

import entity.User;


public interface EventStrategy<T> {
    T getEvents(User user);
}
