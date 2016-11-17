package communication;

import communication.connection.inputConnections.InputConnection;
import communication.receiver.PropertyReceiver;
import communication.receiver.delivery.Delivery;
import communication.requests.Request;

/**
 * Handles received request packages.
 *
 * The requests are delivered asynchronously via the
 * {@link Delivery} class.
 */
public class RequestReceiver extends PropertyReceiver<Request[]> {

    public RequestReceiver(InputConnection<Request[]> connection) {
        super(connection);
    }

    /**
     * Returns a {@link Delivery} that can be used to retrieve the requests
     * once they are delivered.
     * @return an expected delivery. (Works like a "Promise")
     */
    public Delivery<Request[]> expectRequest(){
        return expectDelivery();
    }

}
