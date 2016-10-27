package communication.sender;

/**
 * A package contains header information such as:
 *  - Sender IP address
 *  - Receiver IP address
 *  - Array of Request messages
 */
public class Package {

    private String senderAddress = "";
    private String receiverAddress = "";
    private String[] requests = new String[]{""};

    public Package senderAddress(String address){
        this.senderAddress = address;
        return this;
    }

    public Package receiverAddress(String address){
        this.receiverAddress = address;
        return this;
    }

    public Package setRequests(String[] requests){
        this.requests = requests;
        return this;
    }

    public String toString(){
        String output = "";
        String delimiter = "|";

        output += delimiter;
        output += this.receiverAddress + delimiter;
        output += this.senderAddress + delimiter;

        for(String request : this.requests)
            output += request + delimiter;

        return output;
    }
}
