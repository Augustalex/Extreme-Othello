package tests;

import java.io.PrintStream;

/**
 * A Thread with its own standard output PrintStream. (following the {@link Loggable} interface.
 */
public class LoggableThread extends Thread implements Loggable {

    public PrintStream output = new PrintStream(System.out);

    public LoggableThread(Runnable runnable){
        super(runnable);
    }

    @Override
    public PrintStream getPrintStream() {
        return this.output;
    }

    @Override
    public void setPrintStream(PrintStream printStream) {
        this.output = printStream;
    }
}
