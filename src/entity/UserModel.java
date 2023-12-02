package entity;

import java.util.ArrayList;

class UserModel implements User {

    private final String postalCode;
    private final ArrayList<Artist> favouriteArtist;

    UserModel(String postalCode, ArrayList<Artist> favouriteArtist) {
        this.postalCode = postalCode;
        this.favouriteArtist = favouriteArtist;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public ArrayList<Artist> getFavouriteArtist() {
        return favouriteArtist;
    }


}


