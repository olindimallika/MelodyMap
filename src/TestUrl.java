import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class TestUrl {

    public static List<String> formatClickableUrls(List<String> inputUrls) {
        List<String> clickableUrls = new ArrayList<>();

        for (String url : inputUrls) {
            try {
                // Validate and create URI
                URI uri = new URI(url);
                String clickableUrl = createClickableUrl(uri);
                clickableUrls.add(clickableUrl);
            } catch (URISyntaxException e) {
                // Handle invalid URLs if needed
                System.err.println("Invalid URL: " + url);
            }
        }

        return clickableUrls;
    }

    private static String createClickableUrl(URI uri) {
        // Create HTML-formatted clickable URL
        String clickableUrl = "<a href=\"" + uri.toString() + "\">" + uri.toString() + "</a>";
        return clickableUrl;
    }

    public static void main(String[] args) {
        // Example usage
        List<String> inputUrls = new ArrayList<>();
        inputUrls.add("https://www.example.com");
        inputUrls.add("invalid-url");  // This will be skipped due to URI validation

        List<String> clickableUrls = formatClickableUrls(inputUrls);

        // Print formatted clickable URLs
        for (String clickableUrl : clickableUrls) {
            System.out.println(clickableUrl);
        }
    }
}
