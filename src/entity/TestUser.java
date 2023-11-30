package entity;

import java.util.ArrayList;

public class TestUser implements User {

    private final String name;
    private final ArrayList<Artist> favArtists;
    private final String postalCode;

    public TestUser(String name, ArrayList<Artist> favArtists, String postalCode) {
        this.name = name;
        this.favArtists = favArtists;
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Artist> getFavArtist() {
        return favArtists;
    }

    public String getPostalCode() {
        return postalCode;
    }
}

