package entity;

import java.util.ArrayList;

class UserModel implements User {

//    private final String name;

    private final ArrayList<String> favouriteArtist;

    private final String postalCode;

//    private final String location;

    UserModel(String postalCode, ArrayList<String> favouriteArtist) {
//        this.name = name;
        this.favouriteArtist = favouriteArtist;
        this.postalCode = postalCode;
//        this.location = location;
    }

//    @Override
//    public String getName() {
//        return name;
//    }

    @Override
    public ArrayList<String> getFavArtist() {
        return favouriteArtist;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

//    @Override
//    public String getLocation() {
//        return location;
//    }


}


