package interface_adapter.similar_artist;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SimilarArtistViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Similar Artists Concerts";

    public static final String POSTALCODE_LABEL = "Please enter a Canadian postal code (capital, no space)";

    public static final String FAV_ARTIST_LABEL = "Please enter the names of your favourite artists and type done when " +
            "you are finished";

    public static final String ENTER_BUTTON_LABEL = "Enter";

    private SimilarArtistState state = new SimilarArtistState();

    public SimilarArtistViewModel() {
        super("similar artists on tour");
    }
    public void setState(SimilarArtistState state) {
        this.state = state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public SimilarArtistState getState() {
        return state;
    }

}
