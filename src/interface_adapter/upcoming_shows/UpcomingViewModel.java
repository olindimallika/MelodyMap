package interface_adapter.upcoming_shows;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The UpcomingViewModel class serves as the view model for the upcoming shows functionality in the application.
 * It extends the ViewModel class and provides access to the state and properties of the upcoming shows view.
 */
public class UpcomingViewModel extends ViewModel {

    /**
     * The title label for the upcoming shows view.
     */
    public static final String TITLE_LABEL = "Upcoming Shows View";

    /**
     * The label for entering a Canadian postal code in the upcoming shows view.
     */
    public static final String POSTALCODE_LABEL = "Please enter a Canadian postal code (capital, no space):";

    /**
     * The label for the "Enter" button in the upcoming shows view.
     */
    public static final String ENTER_BUTTON_LABEL = "Enter";

    /**
     * The current state of the upcoming shows view.
     */
    private UpcomingState state = new UpcomingState();

    /**
     * Constructs a new UpcomingViewModel with the specified view name.
     */
    public UpcomingViewModel() {
        super("upcoming shows");
    }


    /**
     * Sets the state of the upcoming shows view model.
     *
     * @param state The new state to set.
     */
    public void setState(UpcomingState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies registered listeners about a property change in the upcoming shows view model.
     */
    public void firePropertyChanged(){
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to the upcoming shows view model.
     *
     * @param listener The listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    public UpcomingState getState(){
        return state;
    }
}