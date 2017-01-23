package tests;

import javafx.scene.text.Text;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Pipes all input bytes (as a regular OutputStream) to an JavaFX Text node.
 */
public class FXPrintStream extends OutputStream {

    private Text text;

    public FXPrintStream(Text text){
        this.text = text;
    }

    @Override
    public void write(int b) throws IOException {
        this.text.setText(this.text.getText() + Byte.toString((byte)b));
    }
}
