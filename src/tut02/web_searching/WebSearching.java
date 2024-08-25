package tut02.web_searching;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//
//public class WebSearching {
//    public static void main(String[] args) throws Exception {
//        URL url = null;
//        BufferedReader bufferedReader = null;
//
//        try {
//            url = new URL("https://lms.fit.hanu.vn/?lang=en");
//        }
//        catch(Exception e) {
//            System.out.println("Cannot find webpage ");
//            System.exit(-1);
//        }
//        try {
//            URLConnection connection = url.openConnection();
//            bufferedReader = new BufferedReader(
//                    new InputStreamReader(connection.getInputStream())
//            );
//        }
//        catch (IOException e) {
//            System.out.println("Cannot connect to webpage ");
//            System.exit(-1);
//        }
//        try {
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                if (line.equals("Java Software Development")) {
//                    System.out.println("true");
//                    return;
//                }
////                System.out.println(line);
//            }
//            bufferedReader.close();
//        } catch (IOException e) {
//            System.out.println("Cannot read from webpage " );
//        }
//    }
//}
//


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebSearching {

    private static final String PHRASE = "Java Software Development";
    private static final Pattern LINK_PATTERN = Pattern.compile("href=\"(.*?)\"", Pattern.CASE_INSENSITIVE);

    public static void main(String[] args) {
        String startUrl = "https://lms.fit.hanu.vn/?lang=en";
        searchForPhrase(startUrl, PHRASE);
    }

    public static void searchForPhrase(String startUrl, String phrase) {
        Set<String> visited = new HashSet<>();
        Queue<String> toVisit = new LinkedList<>();
        toVisit.add(startUrl);

        while (!toVisit.isEmpty()) {
            String url = toVisit.poll();
            if (visited.contains(url)) {
                continue;
            }
            visited.add(url);

            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                if (content.toString().contains(phrase)) {
                    System.out.println("Phrase '" + phrase + "' found at: " + url);
                    return;
                }

                Matcher matcher = LINK_PATTERN.matcher(content);
                while (matcher.find()) {
                    String foundUrl = matcher.group(1);
                    if (!visited.contains(foundUrl)) {
                        toVisit.add(foundUrl);
                    }
                }
            } catch (Exception e) {
                System.out.println("Failed to retrieve " + url + ": " + e.getMessage());
            }
        }

        System.out.println("Phrase '" + phrase + "' not found starting from " + startUrl);
    }
}
