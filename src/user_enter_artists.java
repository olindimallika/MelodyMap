import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class user_enter_artists {
    //stores user's fav artist in list.

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> favoriteArtists = new ArrayList<>();

        // ask the user to enter 5 favorite artists
        for (int i = 0; i < 5; i++) {
            System.out.print("Enter your favorite artist #" + (i + 1) + ": ");
            String artist = scanner.nextLine();
            favoriteArtists.add(artist);
        }

        System.out.print(favoriteArtists);

        // end the scanner
        scanner.close();
    }
}


