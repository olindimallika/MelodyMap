package entity;

class UserModel implements User {

    private final String name;

    private final String[] favouriteArtist;

    private final String location;

    UserModel(String name, String[] favouriteArtist, String location) {
        this.name = name;
        this.favouriteArtist = favouriteArtist;
        this.location = location;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String[] getFavArtist() {
        return favouriteArtist;
    }

    @Override
    public String getLocation() {
        return location;
    }
}
