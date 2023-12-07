package interface_adapter.upcoming_shows;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents the ViewModel for the Upcoming Shows view in the application.
 * It extends the base ViewModel class and provides methods for managing the view state.
 */
public class UpcomingViewModel extends ViewModel{

    // A constant string representing the title label for the Upcoming Shows view.
    public static final String TITLE_LABEL = "                                                                                                            " +
            "                                           " +
            "Upcoming Shows View                                                                                                                          ";

    // A constant string representing the label instructing users to enter a Canadian postal code.
    public static final String POSTALCODE_LABEL = "                                                                                                        " +
            "                     Please enter a Canadian postal code (capital, no space):                                                                       ";

    // A constant string representing the label for the "Enter" button.
    public static final String ENTER_BUTTON_LABEL = "Enter";

    // The internal state object containing information about the Upcoming Shows view.
    private UpcomingState state = new UpcomingState();

    // Constructs an instance of UpcomingViewModel and sets the view name.
    public UpcomingViewModel(){
        super("upcoming shows");
    }

    /**
     * Sets the internal state of the UpcomingViewModel.
     *
     * @param state The new state of the UpcomingViewModel.
     */
    public void setState(UpcomingState state){
        this.state = state;
    }

    // Notifies listeners that the state property has changed.
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // Fires a property change event to notify listeners about a change in the state property.
    public void firePropertyChanged(){
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a PropertyChangeListener to the list of listeners.
     *
     * @param listener The listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the current state of the UpcomingViewModel.
     *
     * @return The current state object representing the state of the UpcomingViewModel.
     */
    public UpcomingState getState(){
        return state;
    }
}
