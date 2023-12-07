package use_case.notify_user_tour;

import java.io.IOException;

/**
 * The NotifyDataAccess interface defines methods for notifying users about tours related to a specific artist
 * and classification.
 */
public interface NotifyDataAccess {

    /**
     * Checks if there is an upcoming tour for the specified artist and classification.
     *
     * @param artistName     The name of the artist for whom to check the tour.
     * @param classification The classification of the tour (e.g., concert, exhibition).
     * @return A string indicating the tour status. Possible values include:
     *         - "Upcoming": Indicates that there is an upcoming tour for the specified artist and classification.
     *         - "No Upcoming Tour": Indicates that there is no upcoming tour for the specified artist and classification.
     * @throws IOException          If an I/O error occurs while accessing data.
     * @throws InterruptedException If the thread is interrupted while waiting for the tour information.
     */
    String hasATour(String artistName, String classification) throws IOException, InterruptedException;
}
