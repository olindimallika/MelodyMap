package entity;

import java.util.ArrayList;

public interface User{

    String getName();

    ArrayList<TestArtist> getFavArtist(); // when done testing turn TestArtist back into Artist

    String getPostalCode();
}
