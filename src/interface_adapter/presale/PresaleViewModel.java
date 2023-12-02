package interface_adapter.presale;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PresaleViewModel extends ViewModel {
    public static final String TITLE_LABEL = "PRESALE DATES";

    public static final String POSTALCODE_LABEL = "Please enter a Canadian postal code (capital, no space):";

    public static final String ARTIST_LABEL = "Please enter the name of your favourite artist!:";

    public static final String ENTER_BUTTON_LABEL = "Enter";

    private PresaleState state = new PresaleState();

    public PresaleViewModel(){
        super("presale");
    }

    public void setState(PresaleState state){
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged(){
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }


    public PresaleState getState() {
        return state;
    }
}
