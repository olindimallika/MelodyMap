package interface_adapter.artist_venue;

import interface_adapter.ViewModel;
import interface_adapter.show_concerts.ShowConcertsState;
import interface_adapter.upcoming_shows.UpcomingState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ArtistViewModel extends ViewModel {

    public final String TITLE_LABEL = "ArtistVenue View";

    private ArtistState state = new ArtistState();

    public static final String PERSONALIZE_BUTTON_LABEL1 = "Click to reload and see if there are presale dates";
    public static final String PERSONALIZE_BUTTON_LABEL2 = "Back";

    public ArtistViewModel() {
        super("show artist concerts");
    }

    public void setState(ArtistState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ArtistState getState() {

        return state;
    }
}

