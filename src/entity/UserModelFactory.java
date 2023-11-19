package entity;

import java.time.LocalDateTime;

public class UserModelFactory implements UserFactory{
    /**
     * Requires: postal code is valid.
     * @param name
     * @param favouriteArtists
     * @param location
     * @param postalCode
     * @return
     */

    @Override
    public User create(String name, String[] favouriteArtists, String location, String postalCode) {
        return new UserModel(name, favouriteArtists, location, postalCode);
    }

}
