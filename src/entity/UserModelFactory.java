package entity;

import java.util.List;

public class UserModelFactory implements UserFactory{
    public User create(String postalCode, List<Artist> favouriteArtists) {
        return new UserModel(postalCode, favouriteArtists);
    }

}
