package entity;


public class UserModelFactory implements UserFactory{
    /**
     * Requires: postal code is valid.
     * @param postalCode
     * @return
     */

    @Override
    public User create(String postalCode) {
        return new UserModel(postalCode);
    }

}
