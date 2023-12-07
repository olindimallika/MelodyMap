package interface_adapter.artist_venue;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The `ArtistViewModel` class serves as the view model for managing and representing
 * artist-related information in the user interface. It extends the `ViewModel` class
 * and incorporates an associated `ArtistState` for handling the state of artist information.
 */
public class ArtistViewModel extends ViewModel {

    /**
     * The state containing information about artists and their shows.
     */
    private ArtistState state = new ArtistState();

    /**
     * The label for the first personalize button, prompting users to see their favorite artist shows.
     */
    public static final String PERSONALIZE_BUTTON_LABEL1 = "Click to see your favorite artist shows";

    /**
     * The label for the second personalize button, allowing users to go back.
     */
    public static final String PERSONALIZE_BUTTON_LABEL2 = "Back";

    /**
     * Constructs an `ArtistViewModel` with the specified view identifier.
     *
     */
    public ArtistViewModel() {
        super("show artist concerts");
    }

    /**
     * Sets the state of the artist view model.
     *
     * @param state The `ArtistState` object representing the state of artist-related information.
     */
    public void setState(ArtistState state) {
        this.state = state;
    }

    /**
     * Fires a property change event to notify observers about a change in the view model state.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies observers about a change in the view model state.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to observe changes in the view model state.
     *
     * @param listener The `PropertyChangeListener` to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Retrieves the current state of the artist view model.
     *
     * @return The `ArtistState` object representing the state of artist-related information.
     */
    public ArtistState getState() {
        return state;
    }
}
