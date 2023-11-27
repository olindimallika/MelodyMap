package entity;

import java.util.ArrayList;

class UserModel implements User {

    private final String name;

    private final ArrayList<Artist
            > favouriteArtist;

    private final String postalCode;
    UserModel(String name, ArrayList<Artist> favouriteArtist, String postalCode) {
        this.name = name;
        this.favouriteArtist = favouriteArtist;
        this.postalCode = postalCode;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ArrayList<Artist> getFavArtist() {
            return favouriteArtist;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

}


