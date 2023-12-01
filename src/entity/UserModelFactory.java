package entity;

import java.util.ArrayList;

public class UserModelFactory implements UserFactory{
    public User create(String postalCode, ArrayList<String> favouriteArtist) {
        return new UserModel(postalCode, favouriteArtist);
    }

}
