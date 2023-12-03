package entity;

class UserModel implements User {

    private final String postalCode;
    private final String favouriteArtist;

    UserModel(String postalCode, String favouriteArtist) {
        this.postalCode = postalCode;
        this.favouriteArtist = favouriteArtist;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String getFavouriteArtist() {
        return favouriteArtist;
    }


}


