package communication;

/**
 * Wrapper class containing IP and other connection
 * information about a client.
 */
public class ConnectionDetails {

    public String hostname;

    public ConnectionDetails(String hostname){
        this.hostname = hostname;
    }
}
