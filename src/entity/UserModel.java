package entity;

class UserModel implements User {

    private final String name;

    private final String postalCode;

    UserModel(String name, String postalCode) {
        this.name = name;
        this.postalCode = postalCode;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }


}


