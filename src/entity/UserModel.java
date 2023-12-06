package entity;

import java.util.List;

class UserModel implements User {

    private final List<Artist> favouriteArtists;
    private final String postalCode;

    public UserModel(String postalCode, List<Artist> favouriteArtists) {
        this.postalCode = postalCode;
        this.favouriteArtists = favouriteArtists;
    }


    @Override
    public List<Artist> getFavouriteArtists() {
        return favouriteArtists;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }


}


