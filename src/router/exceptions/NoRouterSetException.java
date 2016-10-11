package router.exceptions;

/**
 * Created by August on 2016-10-11.
 */
public class NoRouterSetException extends RuntimeException {

    public NoRouterSetException(){
        super("No router set. Cannot get application router.");
    }
}
