package interface_adapter.notify_user_tour;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents the ViewModel for notifying users of upcoming concerts.
 */
public class NotifyViewModel extends ViewModel {

    /**
     * The title label for the Notify User of Upcoming Concerts View.
     */
    public static final String TITLE_LABEL = "Notify User of Upcoming Concerts View";

    /**
     * The label prompting the user to enter 5 of their favorite artists.
     */
    public static final String ARTIST_LABEL = "Enter 5 of your favourite artists (use commas to separate each):";

    /**
     * The label for the button that triggers checking if the entered artists are on tour.
     */
    public static final String CHECK_BUTTON_LABEL = "See if they're on tour";

    /**
     * The state of the NotifyViewModel, including user input and notification results.
     */
    private NotifyState state = new NotifyState();

    /**
     * Constructs a new NotifyViewModel with the view name set to "notify user tour."
     */
    public NotifyViewModel() {
        super("notify user tour");
    }

    /**
     * Sets the state of the NotifyViewModel.
     *
     * @param state The new state for the NotifyViewModel.
     */
    public void setState(NotifyState state) {
        this.state = state;
    }

    /**
     * Fires a property change event for the "state" property.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies registered listeners that a property change has occurred.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to the ViewModel.
     *
     * @param listener The listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the current state of the NotifyViewModel.
     *
     * @return The current state of the NotifyViewModel.
     */
    public NotifyState getState() {
        return state;
    }
}