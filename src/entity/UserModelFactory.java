package entity;


public class UserModelFactory implements UserFactory{
    /**
     * Requires: postal code is valid.
     * @param postalCode
     * @param favouriteArtist
     * @return
     */

    @Override
    public User create(String postalCode, String favouriteArtist) {
        return new UserModel(postalCode, favouriteArtist);
    }

}
