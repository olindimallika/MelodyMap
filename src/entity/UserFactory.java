package entity;

import java.util.ArrayList;
import java.util.List;

public interface UserFactory {
    /** Requires: postal code is valid. */
    User create(String postalCode, List<Artist> favouriteArtists);

    User create(String postalCode, ArrayList<String> favouriteArtist);
}
