package interface_adapter.upcoming_shows;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UpcomingViewModel extends ViewModel{
    public static final String TITLE_LABEL = "                                                                                                            " +
            "                                           " +
            "Upcoming Shows View                                                                                                                          ";

    public static final String POSTALCODE_LABEL = "                                                                                                        " +
            "                     Please enter a Canadian postal code (capital, no space):                                                                       ";

    public static final String ENTER_BUTTON_LABEL = "Enter";

    private UpcomingState state = new UpcomingState();

    public UpcomingViewModel(){
        super("upcoming shows");
    }

    public void setState(UpcomingState state){
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged(){
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    public UpcomingState getState(){
        return state;
    }
}
