package communication.receiver;

import communication.receiver.exceptions.NoPackageInBuffer;
import communication.sender.Package;
import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by August on 2016-10-27.
 */
public class Receiver {

    private Connection connection;

    private final ObjectProperty<Void> receivedPackageNotifier = new SimpleObjectProperty<>(null);

    private final Object bufferKey = new Object();
    private final List<Package> buffer = new ArrayList<>();

    public Receiver(Connection connection){
        try {
            this.connection.connect();
            this.connection = connection;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not connect. Connection not set");

            this.connection = new VoidConnection();
        }
    }

    public Receiver start(){
        waitAndReceive();
        return this;
    }

    public Package popBuffer(){
        synchronized (bufferKey) {
            if (this.buffer.size() <= 0)
                throw new NoPackageInBuffer();
            else
                return this.buffer.get(this.buffer.size());
        }
    }

    private void pushToBuffer(Package payload){
        synchronized (bufferKey){
            this.buffer.add(payload);
            this.receivedPackageNotifier.set(null);
        }
    }

    public void waitAndReceive(){
        new Thread(() -> {
            Package payload = this.connection.receive();
            pushToBuffer(payload);
            waitAndReceive();
        }).start();
    }

    public Delivery expectDelivery(){
        Delivery delivery = new Delivery();

        return delivery;
    }

    public void orderDelivery(Delivery delivery){
        this.receivedPackageNotifier.addListener(new DeliveryNotice(delivery, this));
    }

}
