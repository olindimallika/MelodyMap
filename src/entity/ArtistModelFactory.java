package entity;

import java.time.LocalDateTime;

public class ArtistModelFactory implements ArtistFactory{
    /**
     * @param name
     * @return
     */
    @Override
    public Artist create(String name) {

        return new ArtistModel(name);
    }
}