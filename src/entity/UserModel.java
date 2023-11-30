package entity;

class UserModel implements User {

    private final String postalCode;

    UserModel(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }


}


