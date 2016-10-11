package serverTest;

/**
 * Created by August on 2016-10-10.
 */
public class ServerTest {

    public static void main(String[] args){
        new Thread(()->{
            Server server = new Server();
            server.run();
        }).start();
    }
}