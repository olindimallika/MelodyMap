package entity;

class ArtistModel implements Artist {

    private final String name;

    ArtistModel(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
