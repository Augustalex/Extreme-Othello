package tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.PrintStream;

/**
 * Opens a new window representing a console output screen.
 * Pipes a PrintStream to the window.
 */
public class FloatingConsole extends Application implements Loggable{

    private PrintStream output = new PrintStream(System.out);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Text text = new Text();
        TextFlow textFlow = new TextFlow(text);

        ScrollPane scrollPane = new ScrollPane(textFlow);

        Scene scene = new Scene(scrollPane, 250, 600);
        primaryStage.setScene(scene);

        this.setPrintStream(new PrintStream(new FXPrintStream(text)));

        primaryStage.show();
    }

    @Override
    public PrintStream getPrintStream() {
        return this.output;
    }

    @Override
    public void setPrintStream(PrintStream printStream) {
        this.output = printStream;
    }

    public void main(String[] args){
        launch(args);
    }

}
