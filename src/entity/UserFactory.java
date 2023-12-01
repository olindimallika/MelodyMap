package entity;

import java.util.ArrayList;

public interface UserFactory {
    User create(String postalCode, ArrayList<String> favouriteArtist);
}
