//import java.io.IOException;
//import java.net.URL;
//import java.util.Scanner;
//
//public class TicketmasterAPI extends LocationFinderOld {
//    private final String apiKey;
//
//    public TicketmasterAPI(String apiKey) {
//        this.apiKey = apiKey;
//    }
//
//    public String searchArtist(String artistName) throws IOException {
//        String baseUrl = "https://app.ticketmaster.com/discovery/v2/attractions.json";
//        String urlString = baseUrl + "?keyword=" + artistName + "&apikey=" + apiKey;
//
//        URL url = new URL(urlString);
//        Scanner scanner = new Scanner(url.openStream());
//        StringBuilder jsonContent = new StringBuilder();
//
//        while (scanner.hasNext()) {
//            jsonContent.append(scanner.nextLine());
//        }
//
//        scanner.close();
//        return jsonContent.toString();
//    }
//
//    public String getEvents(String dma) throws IOException {
//        String baseUrl = "https://app.ticketmaster.com/discovery/v2/events.json";
//        String urlString = baseUrl + "?classificationName=music&dmaId=" + dma + "&apikey=" + apiKey;
//
//        URL url = new URL(urlString);
//        Scanner scanner = new Scanner(url.openStream());
//        StringBuilder jsonContent = new StringBuilder();
//
//        while (scanner.hasNext()) {
//            jsonContent.append(scanner.nextLine());
//        }
//
//        scanner.close();
//        return jsonContent.toString();
//    }
//
//    public String getEventsFromLatLong(String latlong) throws IOException {
//        String baseUrl = "https://app.ticketmaster.com/discovery/v2/events.json";
//        String urlString = baseUrl + "?geoPoint=" + latlong + "&apikey=" + apiKey;
//
//        URL url = new URL(urlString);
//        Scanner scanner = new Scanner(url.openStream());
//        StringBuilder jsonContent = new StringBuilder();
//
//        while (scanner.hasNext()) {
//            jsonContent.append(scanner.nextLine());
//        }
//
//        scanner.close();
//        return jsonContent.toString();
//    }
//
//    public String searchUpcomingConcerts(String artistId) throws IOException {
//        String baseUrl = "https://app.ticketmaster.com/discovery/v2/events.json";
//        String urlString = baseUrl + "?attractionId=" + artistId + "&apikey=" + apiKey;
//
//        URL url = new URL(urlString);
//        Scanner scanner = new Scanner(url.openStream());
//        StringBuilder jsonContent = new StringBuilder();
//
//        while (scanner.hasNext()) {
//            jsonContent.append(scanner.nextLine());
//        }
//
//        scanner.close();
//
//        return jsonContent.toString();
//    }
//
//
//    public static void main(String[] args) {
//        // Replace 'YOUR_API_KEY' with your actual Ticketmaster API key.
//        String apiKey = "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";
//
//        TicketmasterAPI ticketmasterAPI = new TicketmasterAPI(apiKey);
//
//        // Search for Adele as an example artist.
//        LocationFinderOld lf = new LocationFinderOld();
//        String latlong = lf.latlongy();
//
//        try {
//
//            System.out.println("\n");
//            String eventL = ticketmasterAPI.getEventsFromLatLong(latlong);
//            System.out.println("All Music Events Based on geoPoint");
//            System.out.println(eventL);
//
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//}