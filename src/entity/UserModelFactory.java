package entity;
import java.util.ArrayList;
public class UserModelFactory implements UserFactory{
    /**
     * Requires: postal code is valid.
     * @param postalCode
     * @param favouriteArtist
     * @return
     */

    @Override
    public User create(String postalCode, ArrayList<Artist> favouriteArtist) {
        return new UserModel(postalCode, favouriteArtist);
    }

}
