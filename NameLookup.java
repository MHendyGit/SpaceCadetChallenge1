import java.io.*;
import java.net.*;

public class NameLookup {
    public static void main(String[] args) throws Exception{
        String emailId = getID();
        String html = getHtml(emailId);
        String name = getName(html);

        System.out.println(name);
    }
    public static String getID() throws IOException {
        InputStreamReader i = new InputStreamReader(System.in);
        BufferedReader b = new BufferedReader(i);

        String emailId = "";
        System.out.println("Enter email id:");
        emailId = b.readLine();

        b.close();
        i.close();

        return emailId;
    }
    public static String getHtml(String emailId) throws MalformedURLException, IOException {
        String html = "";
        String url = "https://www.ecs.soton.ac.uk/people/" + emailId;
        URL urlObj = new URL(url);
        URLConnection urlc = urlObj.openConnection();
        InputStreamReader i = new InputStreamReader(urlc.getInputStream());
        BufferedReader b = new BufferedReader(i);
        String line = b.readLine();
        while (line != null) {
            html = html + line;
            line = b.readLine();
        }

        return html;
    }
    public static String getName(String html) {
        Integer start = html.indexOf("title") + 16;
        Integer end = html.indexOf("\"", start);
        String name = html.substring(start, end);

        return name;
    }
}