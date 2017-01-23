package gameServer.open;

import gameServer.gameEntities.PlayerDetails;
import gameServer.message.iterator.IMessageIterator;
import gameServer.message.iterator.MessageIterator;
import gameServer.message.compiler.MessageCompiler;
import hostDetails.Host;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

/**
 * Created by August on 2017-01-20.
 */
public class OpenServerTester {

    private final String serverIP;
    private final int serverPort;
    private String testId;

    public OpenServerTester(String serverIP, int serverPort, String testId){
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.testId = testId;
    }

    public void testLogOn(){

        try {
            Socket socket = new Socket(serverIP, serverPort);

            System.out.println("Testing LogOn");
            PlayerDetails playerDetails = new PlayerDetails();
            playerDetails.connectionDetails = new Host(socket.getLocalAddress().getHostAddress(), socket.getLocalPort());

            playerDetails.player
                    .setName("August-" + testId)
                    .setRed(200)
                    .setGreen(100)
                    .setBlue(0);

            List<String> logOnMessage = MessageCompiler.logOnMessage(playerDetails, 3);
            sendMessage(logOnMessage, socket.getOutputStream());

            IMessageIterator messageIterator = new MessageIterator(socket.getInputStream()).nextInput();

            System.out.println("Client Received message:");
            printMessage(messageIterator.getInput());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void sendMessage(List<String> message, OutputStream outputStream){
        System.out.println("Client got connection.");

        PrintStream printStream = new PrintStream(outputStream);

        StringBuilder builder = new StringBuilder();
        for(String word : message) {
            builder.append(word+"\0");
        }

        printStream.println(builder.toString());
        printStream.flush();

    }

    private void printMessage(List<String> message){
        System.out.println("Message:");
        for(String word : message){
            System.out.print(word + " ");
        }
    }
}
