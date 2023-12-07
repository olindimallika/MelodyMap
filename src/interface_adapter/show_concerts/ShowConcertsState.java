package interface_adapter.show_concerts;

import java.util.LinkedHashMap;

/**
 * The ShowConcertsState class represents the state of the user interface related to the show concerts functionality.
 * It encapsulates information about retrieved concerts, errors related to concert data, and provides methods to access and modify this state.
 *
 * Instances of this class serve as a container for maintaining and manipulating the state of the UI within the application.
 * This includes storing information about concerts, such as their names and details, as well as any errors encountered during the
 * retrieval or processing of concert data.
 *
 * The class includes a copy constructor, allowing the creation of new instances with the same concert information as an existing object.
 * This can be useful for scenarios where a snapshot of the UI state needs to be preserved or transferred between components.
 *
 * It is important to note that this class does not handle the actual logic of fetching or processing concert data; rather, it focuses on
 * storing and providing access to the UI-related state. The functionality and behavior of this class are closely tied to the corresponding
 * user interface layer of the application.
 *
 * Author: Bea Castro
 */
public class ShowConcertsState {

    // A LinkedHashMap storing information about concerts, where the key represents the concert name,
    // and the value represents additional details or metadata about the concert.
    private LinkedHashMap<String, String> concerts = new LinkedHashMap<>();

    // An optional error message indicating issues related to concert data retrieval or processing
    private String concertsError = null;

    /**
     * Constructs a new instance of ShowConcertsState with the same concert information as the provided copy.
     *
     * @param copy The ShowConcertsState object to copy the concert information from.
     */
    public ShowConcertsState(ShowConcertsState copy){
        concerts = copy.concerts;
    }

    /**
     * Constructs a new, empty instance of ShowConcertsState
     */
    public ShowConcertsState(){
    }

    /**
     * Gets the LinkedHashMap containing information about concerts.
     *
     * @return The LinkedHashMap where keys represent concert names, and values represent details or metadata about the concerts.
     */
    public LinkedHashMap<String, String> getConcerts(){
        return concerts;
    }

    /**
     * Sets the LinkedHashMap containing information about concerts.
     *
     * @param concerts The LinkedHashMap where keys represent concert names, and values represent details or metadata about the concerts.
     */
    public void setConcerts(LinkedHashMap<String, String> concerts){
        this.concerts = concerts;
    }

    /**
     * Sets the error message related to concert data retrieval or processing.
     *
     * @param concertsError The error message describing the reason for the failure.
     */
    public void setConcertsError(String concertsError){
        this.concertsError = concertsError;
    }


}
