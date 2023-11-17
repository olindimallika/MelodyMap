import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class TicketmasterAPI extends LocationFinder{

    private final String apiKey;

    public TicketmasterAPI(String apiKey) {
        this.apiKey = apiKey;
    }

    public String searchArtist(String artistName) throws IOException {
        String baseUrl = "https://app.ticketmaster.com/discovery/v2/attractions.json";
        String urlString = baseUrl + "?keyword=" + artistName + "&apikey=" + apiKey;

        URL url = new URL(urlString);
        Scanner scanner = new Scanner(url.openStream());
        StringBuilder jsonContent = new StringBuilder();

        while (scanner.hasNext()) {
            jsonContent.append(scanner.nextLine());
        }

        scanner.close();
        return jsonContent.toString();
    }

    public String getEvents(String dma) throws IOException {
        String baseUrl = "https://app.ticketmaster.com/discovery/v2/events.json";
        String urlString = baseUrl + "?classificationName=music&dmaId=" + dma + "&apikey=" + apiKey;

        URL url = new URL(urlString);
        Scanner scanner = new Scanner(url.openStream());
        StringBuilder jsonContent = new StringBuilder();

        while (scanner.hasNext()) {
            jsonContent.append(scanner.nextLine());
        }

        scanner.close();
        return jsonContent.toString();
    }

    public String getEventsFromLatLong(String latlong) throws IOException {
        String baseUrl = "https://app.ticketmaster.com/discovery/v2/events.json";
        String urlString = baseUrl + "?geoPoint=" + latlong + "&apikey=" + apiKey;

        URL url = new URL(urlString);
        Scanner scanner = new Scanner(url.openStream());
        StringBuilder jsonContent = new StringBuilder();

        while (scanner.hasNext()) {
            jsonContent.append(scanner.nextLine());
        }

        scanner.close();
        return jsonContent.toString();
    }

    public String searchUpcomingConcerts(String artistId) throws IOException {
        String baseUrl = "https://app.ticketmaster.com/discovery/v2/events.json";
        String urlString = baseUrl + "?attractionId=" + artistId + "&apikey=" + apiKey;

        URL url = new URL(urlString);
        Scanner scanner = new Scanner(url.openStream());
        StringBuilder jsonContent = new StringBuilder();

        while (scanner.hasNext()) {
            jsonContent.append(scanner.nextLine());
        }

        scanner.close();

        return jsonContent.toString();
    }


    public static void main(String[] args) {
        // Replace 'YOUR_API_KEY' with your actual Ticketmaster API key.
        String apiKey = "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";

        TicketmasterAPI ticketmasterAPI = new TicketmasterAPI(apiKey);

        // Search for Adele as an example artist.
        String artistName = "Adele";
        String dma = "527";
        LocationFinder lf = new LocationFinder();
        String latlong = lf.latlongy();


        try {
            String artistData = ticketmasterAPI.searchArtist(artistName);
            System.out.println(artistData);

            System.out.println("\n");
            String eventData = ticketmasterAPI.getEvents(dma);
            System.out.println("All Music Events in Toronto:");
            System.out.println(eventData);

            System.out.println("\n");
            String eventL = ticketmasterAPI.getEventsFromLatLong(latlong);
            System.out.println("All Music Events Based on geoPoint");
            System.out.println(eventL);

            char[] artistId = artistData.toCharArray();

            //"ticketmasterAPI.getArtistId(artistName);// Replace with the actual artist ID.
            System.out.println(artistId);
            String artistID = "o";
            if (artistId != null) {
                String upcomingConcerts = ticketmasterAPI.searchUpcomingConcerts(artistID);

                // Parse the upcoming concerts data to extract event details.
                // Note: You may need to use a JSON library to parse the data effectively.

                if (upcomingConcerts != null) {
                    // Print event details.
                    System.out.println("Upcoming " + artistName +  " Concerts:");
                    System.out.println(upcomingConcerts);
                } else {
                    System.out.println("No upcoming events found for this artist.");
                }

            } else {
                System.out.println("No artist found for " + artistName);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}