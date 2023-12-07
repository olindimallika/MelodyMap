package interface_adapter.show_artist_concerts;

import interface_adapter.ViewModel;
import interface_adapter.show_concerts.ShowConcertsState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ShowArtistViewModel extends ViewModel {

    public final String TITLE_LABEL = "Artist Concerts";

    private ShowArtistState state = new ShowArtistState();

    public static final String PERSONALIZE_BUTTON_LABEL = "Click to reload and see if there are presale dates";;


    public ShowArtistViewModel() {
        super("show all concerts");
    }

    public void setState(ShowArtistState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ShowArtistState getState() {
        return state;
    }
}
