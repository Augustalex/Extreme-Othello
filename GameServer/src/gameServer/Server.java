package gameServer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by August on 2017-01-18.
 */
public abstract class Server {

    private BooleanProperty serverOn = new SimpleBooleanProperty(false);
    protected ExecutorService executorService = Executors.newFixedThreadPool(100);

    public abstract void start();

    public abstract void handleClient(Socket socket) throws IOException;

    public BooleanProperty serverOnProperty(){
        return serverOn;
    }
}
