package entity;

class ArtistModel implements Artist {

    private final String name;

    private final String genre;

    private final String artistId;

    ArtistModel(String name, String genre, String artistId) {
        this.name = name;
        this.genre = genre;
        this.artistId = artistId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getGenre() {
        return genre;
    }

    @Override
    public String getArtistId() {
        return artistId;
    }
}
