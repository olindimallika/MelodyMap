package entity;

import java.util.List;

public class UserBuilder {
    private String postalCode;
    private List<Artist> favouriteArtists;
    public UserBuilder(){
    }

    public UserBuilder addPostalCode(String postalCode){
        this.postalCode = postalCode;
        return this;
    }
    public UserBuilder addFavouriteArtist(List<Artist> favouriteArtists){
        this.favouriteArtists = favouriteArtists;
        return this;
    }
    public User build() {
        return new UserModel(postalCode, favouriteArtists);
    }

}
