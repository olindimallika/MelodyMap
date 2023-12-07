package interface_adapter.similar_artist;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SimilarArtistViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Click here to see concerts of similar artists";

    public static final String POSTALCODE_LABEL = "Please enter a Canadian postal code (capital, no space):";

    public static final String ARTIST_LABEL = "Please enter the name of your favourite artist!:";

    public static final String ENTER_BUTTON_LABEL = "Enter";

    // The state of the SimilarArtistViewModel.
    private SimilarArtistState state = new SimilarArtistState();

    /**
     * Constructor for SimilarArtistViewModel.
     * Initializes the view model with a specific title.
     */
    public SimilarArtistViewModel() {
        super("similar artists on tour");
    }

    /**
     * Sets the state of the SimilarArtistViewModel.
     *
     * @param state The new state to set.
     */
    public void setState(SimilarArtistState state) {
        this.state = state;
    }

    // Property change support to notify listeners of state changes.
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies listeners about a change in the state.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to the view model.
     *
     * @param listener The listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Retrieves the current state of the SimilarArtistViewModel.
     *
     * @return The current state.
     */
    public SimilarArtistState getState() {
        return state;
    }

}