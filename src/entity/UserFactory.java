package entity;

import java.util.List;

public interface UserFactory {
    /** Requires: postal code is valid. */
    User create(String postalCode, List<Artist> favouriteArtists);

}
