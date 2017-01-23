package communication.sender;

import communication.ConnectionDetails;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Sends {@link Package} through a connection.
 */
public class Sender {

    private Socket socket = null;

    public Sender(ConnectionDetails connectionDetails, int port) throws IOException {
        System.out.println(connectionDetails);
        System.out.println(port);
        this.socket = new Socket(connectionDetails.hostname, port);
    }

    public void send(Package payload) throws IOException {
        PrintStream output = new PrintStream(this.socket.getOutputStream());
        output.print(PackageCompiler.encode(payload));
        output.flush();
    }
}
