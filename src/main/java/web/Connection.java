package web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Class is used to send HTTP requests to server.
 * @author mshroom
 */
public class Connection {
    private URL url;
    private HttpURLConnection connection;
    
    /**
     * Creates a connection to the given url.
     * @param url The url address.
     * @throws Exception if the connection fails.
     */
    public void connectTo(String url) throws Exception {
        this.url = new URL(url);
        this.connection = (HttpURLConnection) this.url.openConnection();
    }
    
    /**
     * Closes the current connection.
     * @throws Exception if the attempt fails.
     */
    public void close() throws Exception {
        this.connection.disconnect();
    }
    
    /**
     * Sends a GET request to the current url address.
     * @return A String containing the response.
     * @throws Exception if the request fails.
     */
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
    
    /**
     * Sends a POST request to the current url with given parameters.
     * @param queryParameters The query parameters to be sent.
     * @param contentType The content type of the request.
     * @return A String containing the response.
     * @throws Exception if the request fails.
     */
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
