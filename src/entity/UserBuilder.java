package entity;

public class UserBuilder {
    private String postalCode;
    private String favouriteArtist;
    public UserBuilder(){
    }

    public UserBuilder addPostalCode(String postalCode){
        this.postalCode = postalCode;
        return this;
    }


    public User build() {
        return new UserModel(postalCode, favouriteArtist);
    }

}
