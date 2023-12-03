package entity;

public interface UserFactory {
    /** Requires: postal code is valid. */
    User create(String postalCode, String favouriteArtist);

}
