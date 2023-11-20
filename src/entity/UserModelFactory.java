package entity;


public class UserModelFactory implements UserFactory{
    /**
     * Requires: postal code is valid.
     * @param name
     * @param postalCode
     * @return
     */

    @Override
    public User create(String name, String postalCode) {
        return new UserModel(name, postalCode);
    }

}
