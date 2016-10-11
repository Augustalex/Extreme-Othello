package serverTest;

import java.io.IOException;

/**
 * Created by August on 2016-10-10.
 */
public class ServerClientTest {

    public static void main(String[] args){
        Server server = new Server();
        Client client = new Client();

        try {
            new Thread(() -> {
                try{
                    server.run();
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }).start();

            new Thread(()->{
                try{
                    client.run();
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }).start();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
