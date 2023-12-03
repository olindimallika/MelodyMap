package interface_adapter.artist_menu_tour;

import interface_adapter.ViewModel;
import interface_adapter.upcoming_shows.UpcomingState;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ArtistMenuViewModel extends ViewModel {

    private String selectedArtist;

    public static final String TITLE_LABEL = "Artist Menu View";

    private ArtistMenuState state = new ArtistMenuState();

    public static final String BUTTON_LABEL1 = "On Tour";

    public static final String BUTTON_LABEL2 = "Not on Tour. Click to see if their Similar Artists are on Tour";

    public ArtistMenuViewModel() {
        super("notify user tour");

    }

    public String getSelectedArtist() {
        return selectedArtist;
    }

    public void setState(ArtistMenuState state) {
        this.state = state;
        firePropertyChanged();
    }

    public void setSelectedArtist(String selectedArtist) {
        this.selectedArtist = selectedArtist;
        firePropertyChanged();
    }

    public final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ArtistMenuState getState() {
        return state;
    }

}
