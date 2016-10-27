package communication.requests;

/**
 * Created by August on 2016-10-27.
 */
public class RequestEncoder {

    public static final String unsupportedTypeIdentifier = "$";

    public static String encodeToString(Request request){
        String output = "";

        switch(request.type){
            case QEUSTION:
                output += "?";
                break;
            case ORDER:
                output += "+";
                break;
            case DEMAND:
                output += "!";
                break;
            default:
                output += RequestEncoder.unsupportedTypeIdentifier;
        }

        output += request.message;

        return output;
    }
}
