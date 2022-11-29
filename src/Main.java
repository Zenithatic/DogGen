import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.awt.Desktop;

public class Main {
	public static void main(String[] args) throws IOException, URISyntaxException {
		URL url;
		
		// Get dog image
		try {
			// set url
			url = new URL("https://dog.ceo/api/breeds/image/random");
			
			// connect to api
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			
			// get response code
			int responseCode = connection.getResponseCode();
			
			if (responseCode == 200) {
				// retrieve dog image if code 200
				Scanner scanner = new Scanner(url.openStream());
				String json = "";
				
				// Add all retrieved stream text to string
				while (scanner.hasNext()) {
					json += scanner.nextLine();
				}
				
				// Find dog image string from json
				String dogurl = "";
				int startIndex = 12;
				
				while (json.charAt(startIndex) != '"') {
					dogurl += json.charAt(startIndex);
					startIndex++;
				}
				
				dogurl = dogurl.replaceAll("\\\\", "");
				
				// Open dog image in default browser
				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().browse(new URI(dogurl));
				}
				
			}
			else {
				// notify that error 401 or 404 occured
				throw new RuntimeException("HttpResponseCode: " + responseCode);
			}
			
		} catch (MalformedURLException e) {
			// generate error message
			e.printStackTrace();
		}
	}
}
