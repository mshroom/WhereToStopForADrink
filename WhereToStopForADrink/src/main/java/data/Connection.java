package data;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.*;

/**
 *
 * @author mshroom
 */
public class Connection {
    private URL url;
    private HttpURLConnection connection;
    
    public Connection(String url) throws Exception {
        this.url = new URL(url);
        this.connection = (HttpURLConnection) this.url.openConnection(); 
    }
    
    public void close() throws Exception {
        this.connection.disconnect();
    }
    
    public String getRequest() throws Exception {
        connection.setRequestMethod("GET");
        int status = connection.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        String content = "";
        while ((inputLine = in.readLine()) != null) {
            content += inputLine;
        }
        in.close();
        return content;
    }
    
    public String postRequest(String queryParameters, String contentType) throws Exception {
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", contentType);
        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(queryParameters);
        wr.flush();
        wr.close();
        int status = connection.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        String content = "";
        while ((inputLine = in.readLine()) != null) {
            content += inputLine;
        }
        in.close();
        return content;
    }
    
}
