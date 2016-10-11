package serverTest;

/**
 * Created by August on 2016-10-10.
 */
public class ClientTest {
    public static void main (String[] args){
        new Thread(()->{
            Client client = new Client();
            client.run();
        }).start();
    }
}
