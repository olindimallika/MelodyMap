package entity;

public class TestArtist implements Artist {

    private final String name;
    private final String genre;

    private final String artistId;


    public TestArtist(String name, String genre, String artistId) {
        this.name = name;
        this.genre = genre;
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getArtistId() {
        return artistId;
    }
}

