import jdk.dynalink.StandardOperation;
import netscape.javascript.JSObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt"));
            for (int i = 0; i < 10; i++) {
                Thread.sleep(5000);
                long startCheck = System.currentTimeMillis();

                URL url = new URL("https://tvgo.orange.pl/gpapi/status");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int responseCode = connection.getResponseCode();

                long endCheck = System.currentTimeMillis();
                long totalTime = (endCheck - startCheck) % 1000;
                if (responseCode == 200) {
                    System.out.println(new Date()+" response Code: " + responseCode + " time: " + totalTime + "ms");
                    writer.write(new Date()+" response Code: " + responseCode + " time: " + totalTime + "ms\n");
                } else {
                    System.out.println("Wrong Code");
                }
            }
            writer.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
