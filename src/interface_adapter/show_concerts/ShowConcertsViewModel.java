package interface_adapter.show_concerts;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ShowConcertsViewModel extends ViewModel {
    public final String TITLE_LABEL = "Logged In View";

    private ShowConcertsState state = new ShowConcertsState();

    public static final String PERSONALIZE_BUTTON_LABEL = "Don't see anyone you like? Click to personalize.";

    public ShowConcertsViewModel() {
        super("show concerts");
    }

    public void setState(ShowConcertsState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ShowConcertsState getState() {
        return state;
    }

}