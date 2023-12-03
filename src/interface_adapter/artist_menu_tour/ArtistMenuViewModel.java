package interface_adapter.artist_menu_tour;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ArtistMenuViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Artist Menu View";
    private ArtistMenuState state = new ArtistMenuState();

    public static final String BUTTON_LABEL1 = "On Tour";

    public static final String BUTTON_LABEL2 = "Not on Tour. Click to see if their Similar Artists are on Tour";

    public ArtistMenuViewModel() {
        super("notify user tour");

    }

    public void setState(ArtistMenuState state) {
        this.state = state;
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
