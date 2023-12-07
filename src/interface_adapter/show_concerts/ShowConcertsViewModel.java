package interface_adapter.show_concerts;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ShowConcertsViewModel class represents the view model for the user interface related to the show concerts functionality.
 * It extends the ViewModel class, providing methods to manage the state and trigger property changes for UI updates.
 *
 * This class encapsulates information such as the title label, a ShowConcertsState object representing the UI state,
 * and additional labels specific to the show concerts view. It also supports property change listeners, allowing external components
 * to subscribe to changes in the view model's state.
 *
 * Instances of this class serve as a central point for managing and accessing the UI-related state of the show concerts feature.
 * The class follows the Model-View-ViewModel (MVVM) architectural pattern, where the view model acts as a mediator between the view
 * (user interface) and the underlying data and logic (in this case, the ShowConcertsState).
 *
 * The firePropertyChanged method is used to notify registered property change listeners when the view model's state changes.
 * This is crucial for ensuring that the user interface reflects the most up-to-date information.
 *
 * It is important to note that this class is part of the interface adapter layer, and its primary responsibility is to manage the state
 * and interactions related to the show concerts feature. The specifics of UI rendering and event handling are assumed to be implemented
 * elsewhere, potentially in collaboration with a user interface framework.
 *
 * Author: Bea Castro
 * @see ViewModel
 * @see ShowConcertsState
 */
public class ShowConcertsViewModel extends ViewModel {
    // Title label for the show concerts view
    public final String TITLE_LABEL = "Logged In View";

    // The state of the show concerts view model, encapsulating information about concerts and related UI elements
    private ShowConcertsState state = new ShowConcertsState();

    // The label for the personalize button, encouraging users to personalize their concert recommendation
    public static final String PERSONALIZE_BUTTON_LABEL = "Don't see anyone you like? Click to personalize.";

    /**
     * Constructs a new instance of ShowConcertsViewModel with the specified view name.
     * @see ViewModel
     */
    public ShowConcertsViewModel() {
        super("show concerts");
    }

    public void setState(ShowConcertsState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies registered property change listeners about changes in the view model's state.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to the view model.
     *
     * @param listener The property change listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the current state of the show concerts view model.
     *
     * @return The ShowConcertsState object representing the UI state.
     */
    public ShowConcertsState getState() {
        return state;
    }

}
