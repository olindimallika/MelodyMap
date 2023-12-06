package entity;
import java.util.ArrayList;
import java.util.List;

public class UserModelFactory implements UserFactory{
    @Override
    public User create(String postalCode, List<Artist> favouriteArtists) {
        return new UserModel(postalCode, favouriteArtists);
    }

    @Override
    public User create(String postalCode, ArrayList<String> favouriteArtist) {
        return null;
    }

}
