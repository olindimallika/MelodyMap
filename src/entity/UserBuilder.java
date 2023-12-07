package entity;
import java.util.ArrayList;

import java.util.ArrayList;

public class UserBuilder {
    private String postalCode;
    private ArrayList<Artist> favouriteArtist;
    public UserBuilder(){
    }
    public UserBuilder addPostalCode(String postalCode){
        this.postalCode = postalCode;
        return this;
    }

    public UserBuilder addFavouriteArtist(ArrayList<Artist> favouriteArtist){
        this.favouriteArtist = favouriteArtist;
        return this;
    }

    public User build() {
        return new UserModel(postalCode, favouriteArtist);
    }

}