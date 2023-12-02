package entity;

import java.util.ArrayList;

public interface UserFactory {
    /** Requires: postal code is valid. */
    User create(String postalCode, ArrayList<Artist> favouriteArtist);

}
