package communication.receiver;

import communication.receiver.delivery.Delivery;

/**
 * Receives objects and buffers them. Delivery objects can be
 * demanded that will be set with new incoming values.
 * @param <T>
 */
public interface Receiver<T> {

    /**
     * Returns a Delivery object that will be set with a payload
     * as soon at it is received.
     * @return
     */
    Delivery<T> expectDelivery();

    /**
     * Returns a Delivery object that will be set only when a certain
     * object corresponding to the reference parameter, is received.
     *
     * Looming deliveries will filter any other outgoing deliveries according
     * to a queue of Looming deliveries and the order of that queue.
     * @return
     */
    Delivery<T> loomingDelivery(T reference);

}
