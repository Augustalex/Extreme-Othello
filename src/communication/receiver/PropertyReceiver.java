package communication.receiver;

import communication.OneTimeChangeListener;
import communication.OneTimeListChangeListener;
import communication.connection.inputConnections.InputConnection;
import communication.connection.inputConnections.VoidInputConnection;
import communication.receiver.delivery.Delivery;
import communication.receiver.delivery.LoomingDelivery;
import communication.receiver.delivery.PropertyDelivery;
import communication.receiver.exceptions.NoPackageInBuffer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Handles receives from a connection with Java Properties.
 * @param <T>
 */
public class PropertyReceiver<T> implements Receiver<T>{

    private InputConnection<T> connection;

    /**
     * Notifies when an element is pushed onto the buffer.
     */
   // private final ObjectProperty<Void> receivedPackageNotifier = new SimpleObjectProperty<>(null);

    private final Object bufferKey = new Object();
    private final ObservableList<T> buffer = FXCollections.observableArrayList();

    private final Queue<LoomingDelivery<T>> loomingDeliveries = new LinkedBlockingQueue<>();

    public PropertyReceiver(InputConnection<T> connection){
        this.connection = connection;
        this.start();
    }

    /**
     * Returns a delivery that can be used to plan action before
     * anything is really available.
     *
     * @return
     */
    @Override
    public Delivery<T> expectDelivery(){
        Delivery<T> delivery = new PropertyDelivery<>();
        System.out.println("Now expecting a delivery: " + delivery);
        requestDelivery(delivery);

        return delivery;
    }

    @Override
    public Delivery<T> loomingDelivery(T reference) {
        LoomingDelivery<T> loomingDelivery = new LoomingDelivery<>(reference);
        this.loomingDeliveries.add(loomingDelivery);

        return loomingDelivery;
    }

    /**
     * Starts a connection and the waitAndReceive method.
     * @return
     */
    private PropertyReceiver start(){
        new Thread(() -> {
            try {
                System.out.println("Trying to establish connection: " + this.connection);
                this.connection.connect();
                System.out.println("Established a connection.");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Could not requestConnection. InputConnection not set");

                this.connection = new VoidInputConnection<>();
            }
            waitAndReceive();
        }).start();

        return this;
    }

    /**
     * Returns an element from the buffer.
     * @return
     */
    private T popBuffer(){
        synchronized (bufferKey) {
            if (this.buffer.size() <= 0)
                throw new NoPackageInBuffer();
            else
                return this.buffer.get(this.buffer.size());
        }
    }

    /**
     * Pushes an element to the buffer.
     * @param payload
     */
    private void pushToBuffer(T payload){
        synchronized (bufferKey){
            this.buffer.add(payload);
            System.out.println("Added " + payload + " to buffer. Setting property now.");
            //this.receivedPackageNotifier.set(null);
            System.out.println("Property set.");
        }
    }

    /**
     * Continuously waits for incoming elements on the
     * {@link InputConnection} and pushes them onto the buffer.
     */
    private void waitAndReceive(){
        new Thread(() -> {
            try {
                T payload = null;
                payload = this.connection.receive();
                System.out.println("Received " + payload + ", pushing to buffer.");

                pushIfNotNull(passThroughLoomingDeliveries(payload));
                waitAndReceive();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Requests an element for a delivery.
     * @param delivery
     */
    private void requestDelivery(Delivery<T> delivery){
       /* this.receivedPackageNotifier.addListener(new OneTimeChangeListener<>((observable, oldValue, newValue) -> {
            T incoming = this.popBuffer();

            System.out.println("Delivery arrived in buffer: " + incoming);
            delivery.deliver(incoming);
        }));*/

        this.buffer.addListener(new OneTimeListChangeListener<T>(c -> {
            T incoming = this.popBuffer();

            System.out.println("Delivery arrived in buffer: " + incoming);
            delivery.deliver(incoming);
        }));

        System.out.println("Now set to wait for buffer to update. Expecting delivery for: " + delivery);
    }

    /**
     * Will either return the passed payload or return
     * null if the payload was consumed by a Looming Delivery.
     * @param payload
     * @return
     */
    private T passThroughLoomingDeliveries(T payload){

        for(LoomingDelivery<T> loomingDelivery : this.loomingDeliveries){
            if(loomingDelivery.test(payload)){
                loomingDelivery.deliver(payload);
                System.out.println("Looming delivery: " + loomingDelivery);
                System.out.println("Looming delivery payload was: " + payload);
                return null;
            }
        }

        return payload;
    }

    /**
     * Pushes payload to buffer if not null.
     *
     * Mainly used when a possibly consuming filter is passed onto the argument.
     * @param payload
     */
    private void pushIfNotNull(T payload){
        if(payload != null)
            this.pushToBuffer(payload);
    }

}
