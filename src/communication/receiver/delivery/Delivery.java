package communication.receiver.delivery;

import communication.OneTimeChangeListener;
import javafx.beans.value.ChangeListener;

/**
 * Much like an ObjectProperty but with the capability of dealing with cancellation.
 *
 * @param <T>
 */
public interface Delivery<T> {

    void onCancel(OneTimeChangeListener<Boolean> onCancelListener);

    boolean isCanceled();

    void cancel();

    void onDelivery(OneTimeChangeListener<T> onDeliveryListener);

    boolean hasPayload();

    void deliver(T payload);

    T getPayload();
}
