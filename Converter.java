import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Converter {
    private static final String urlStr = "http://www.nbg.ge/rss.php";

    public static double getToUSD(double gel) throws IOException {
        return gel / getCurrency();
    }

    private static double getCurrency() throws IOException{
        URL url = new URL(urlStr);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        String ln;
        do{
            ln = reader.readLine();
            if(ln.contains("USD")) break;
        }
        while(ln != null);

        for (int i = 0; i < 2; i++)
            ln = reader.readLine();

        int st = ln.indexOf('>');
        int end = ln.indexOf('<', st++);

        return Double.valueOf(ln.substring(st, end));
    }
}
