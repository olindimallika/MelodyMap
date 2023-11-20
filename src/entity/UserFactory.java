package entity;

import java.time.LocalDateTime;

public interface UserFactory {
    /** Requires: postal code is valid. */
    User create(String name, String[] favouriteArtists, String location, String postalCode);

}
