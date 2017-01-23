package tests;

/**
 * Created by August on 2016-11-21.
 */
public class NoCurrentLoggable extends RuntimeException {

    public NoCurrentLoggable(){
        super("There is no current Loggable available.");
    }
}
