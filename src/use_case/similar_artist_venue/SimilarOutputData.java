package use_case.similar_artist_venue;

import entity.Artist;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class SimilarOutputData {
        private String finalFormatOutputSimilarArtist = "";


        public SimilarOutputData(String finalFormatOutputSimilarArtist) {
            this.finalFormatOutputSimilarArtist = finalFormatOutputSimilarArtist;
        }


        public String getFormatOutputSimilarArtist() {
            return finalFormatOutputSimilarArtist;
        }

}
