package tests;

import communication.GameServer;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by August on 2016-11-21.
 */
public class ClientTest implements Runnable{

    private final FloatingConsole console = new FloatingConsole();

    @Override
    public void run(){
        try {
            GameServer server = new GameServer(1338);
            //setupConsoleOutput();
            System.out.println("hello world!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void start(){
        new LoggableThread(this).start();
    }
/*
    private void setupConsoleOutput(){
      //  this.setPrintStream(new PrintStream(
                new OutputStream() {
                    @Override
                    public void write(int b) throws IOException {
                        console.getPrintStream().write(b);
                    }
                }
        ));
    }*/

}

