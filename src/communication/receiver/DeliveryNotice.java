package communication.receiver;

import communication.sender.Package;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Created by August on 2016-10-27.
 */
public class DeliveryNotice implements ChangeListener<Void> {

    private Delivery delivery;
    private Receiver receiver;

    public DeliveryNotice(Delivery delivery, Receiver receiver){
        this.delivery = delivery;
        this.receiver = receiver;
    }

    @Override
    public void changed(ObservableValue<? extends Void> observable, Void oldValue, Void newValue) {
        this.delivery.packageProperty().set(this.receiver.popBuffer());

        observable.removeListener(this);
    }
}
