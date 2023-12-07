package interface_adapter.show_artist_concerts;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The `ShowArtistViewModel` class serves as the view model for managing and representing
 * information related to displaying an artist's concerts. It extends the `ViewModel` class
 * and incorporates an associated `ShowArtistState` for handling the state of artist concerts.
 */
public class ShowArtistViewModel extends ViewModel {


    /**
     * The state containing details of all concerts and associated error messages.
     */
    private ShowArtistState state = new ShowArtistState();

    /**
     * The label for the personalize button, prompting users to reload and check for presale dates.
     */
    public static final String PERSONALIZE_BUTTON_LABEL = "Click to reload and see if there are presale dates";

    /**
     * Constructs a `ShowArtistViewModel` with the specified view identifier.
     */
    public ShowArtistViewModel() {
        super("show all concerts");
    }

    /**
     * Sets the state of the show artist view model.
     *
     * @param state The `ShowArtistState` object representing the state of artist concerts.
     */
    public void setState(ShowArtistState state) {
        this.state = state;
    }

    /**
     * Fires a property change event to notify observers about a change in the view model state.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies observers about a change in the view model state.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to observe changes in the view model state.
     *
     * @param listener The `PropertyChangeListener` to be added.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Retrieves the current state of the show artist view model.
     *
     * @return The `ShowArtistState` object representing the state of artist concerts.
     */
    public ShowArtistState getState() {
        return state;
    }
}
