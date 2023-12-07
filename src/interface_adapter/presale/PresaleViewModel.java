package interface_adapter.presale;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
 * The {@code PresaleViewModel} class represents the ViewModel for the presale view.
 * It manages the state and properties related to the presale view, including title labels,
 * input labels, buttons, and interaction with the {@code PresaleState}.
 */

public class PresaleViewModel extends ViewModel {

    /**
     * The title label for the presale view.
     */
    public static final String TITLE_LABEL = "PRESALE DATES";

    /**
     * The label for the postal code input field in the presale view.
     */
    public static final String POSTALCODE_LABEL = "Please enter a Canadian postal code (capital, no space):";


    /**
     * The label for the artist input field in the presale view.
     */
    public static final String ARTIST_LABEL = "Please enter the name of your favourite artist!:";

    /**
     * The label for the enter button in the presale view.
     */
    public static final String ENTER_BUTTON_LABEL = "Enter";

    /**
     * The state object holding the current state of the presale view.
     */
    private PresaleState state = new PresaleState();


    /**
     * Constructs a new instance of {@code PresaleViewModel}.
     */
    public PresaleViewModel(){
        super("presale");
    }

    /**
     * Sets the state of the presale view model.
     *
     * @param state The new state to set.
     */
    public void setState(PresaleState state){
        this.state = state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    /**
     * Fires a property change event to notify listeners about a change in the state.
     */
    public void firePropertyChanged(){
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to the presale view model.
     *
     * @param listener The listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }



    /**
     * Gets the current state of the presale view model.
     *
     * @return The current state.
     */

    public PresaleState getState() {
        return state;
    }
}
