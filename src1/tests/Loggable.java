package tests;

import java.io.PrintStream;

/**
 * An interface for accessing an own standard output PrintStream.
 */
public interface Loggable {

    static Loggable getCurrentLoggable(){
        Thread currentThread = Thread.currentThread();
        if(currentThread instanceof Loggable)
            return (Loggable) currentThread;
        else
            throw new NoCurrentLoggable();
    }

    static boolean hasCurrentLoggable(){
        return Thread.currentThread() instanceof Loggable;
    }

    PrintStream getPrintStream();

    void setPrintStream(PrintStream printStream);
}
