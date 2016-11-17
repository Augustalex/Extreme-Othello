package communication.receiver.delivery;

/**
 * A looming delivery contains a reference object that tells
 * what object is expected to be delivered. Only an object
 * equal to the reference should be set in this delivery.
 * @param <T>
 */
public class LoomingDelivery<T> extends PropertyDelivery<T> {

    private T reference;

    public LoomingDelivery(T reference){
        this.reference = reference;
    }

    public T getReference(){
        return this.reference;
    }

    public boolean test(T payload){
        return this.reference.equals(payload);
    }
}
