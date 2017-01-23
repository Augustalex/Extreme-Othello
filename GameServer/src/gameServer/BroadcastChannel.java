package gameServer;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Contains a list of outputs. Is used to send
 * a String message as a broadcast to all outputs
 * in the list.
 */
public class BroadcastChannel {

    private List<OutputStream> outputs = new ArrayList<>();

    public BroadcastChannel addOutputs(OutputStream... outputs){
        Collections.addAll(this.outputs, outputs);

        return this;
    }

    public void removeOutput(OutputStream output){
        outputs.remove(output);
    }

    public BroadcastChannel broadcast(String message){
        for(OutputStream output : outputs)
            new PrintStream(output).print(message);

        return this;
    }

    public BroadcastChannel exclusiveBroadcast(String message, OutputStream excludedOutput){
        for(OutputStream output : outputs)
            if(!output.equals(excludedOutput))
                new PrintStream(output).print(message);

        return this;
    }
}
