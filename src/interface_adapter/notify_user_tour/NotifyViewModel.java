package interface_adapter.notify_user_tour;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NotifyViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Notify User of Upcoming Concerts View";

    public static final String ARTIST_LABEL = "Enter 5 of your favourite artists (use commas with no spaces to separate each):";

    public static final String CHECK_BUTTON_LABEL = "See if they're on tour";

    private NotifyState state = new NotifyState();

    public NotifyViewModel() {
        super("notify user tour");
    }

    public void setState(NotifyState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public NotifyState getState() {
        return state;
    }
}