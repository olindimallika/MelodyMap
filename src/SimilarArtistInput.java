import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimilarArtistInput {
    private static final String CLIENTKEY = "d8de2f3b15464375938c514ed2e44270";

    public static ArrayList<String> favouriteArtists = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<String> similar_Artists() {

        System.out.print("Enter your favourite artists ): ");
        while(true) {

            String check =(scanner.nextLine());

            if (check.equalsIgnoreCase("done")) {
                break;
            }
            else{
                favouriteArtists.add(check);
            }
        }
        System.out.println(favouriteArtists);
        return favouriteArtists;
    }

    public static void main(String [] args) {
        similar_Artists();
    }

    }
