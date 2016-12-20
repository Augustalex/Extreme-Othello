package tests;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Redirects System.out to a Threads own defined PrintStream (given that the current Thread
 * instance implements the {@link Loggable} interface.
 */
public class OutputThreadDelegator extends PrintStream {

    private PrintStream output = System.out;

    public OutputThreadDelegator() {
        super(System.out);
    }

    @Override
    public void flush() {
        getActivePrintStream().flush();
    }

    @Override
    public void close() {
        getActivePrintStream().close();
    }

    @Override
    public boolean checkError() {
        return getActivePrintStream().checkError();
    }

    @Override
    public void write(byte[] b) throws IOException, RuntimeException {
        getActivePrintStream().write(b);
    }

    @Override
    public void write(int b) {
        getActivePrintStream().write(b);
    }

    @Override
    public void write(byte[] buf, int off, int len) {
        getActivePrintStream().write(buf, off, len);
    }

    private PrintStream getActivePrintStream(){
        if(Loggable.hasCurrentLoggable())
            return Loggable.getCurrentLoggable().getPrintStream();
        else
            return this.output;
    }


}
