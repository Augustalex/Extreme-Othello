package boardGameLibrary.views.javaFxViews;

import utilities.router.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * Created by August on 2016-10-10.
 */
public abstract class FXMLViewController implements ViewController {

    private String resourceName;
    protected Pane container;

    public FXMLViewController(String resourceName){
        this.resourceName = resourceName;
    }

    private Parent loadFXML(Class classObject, Initializable controller){
        Parent fxml = null;
        try{
            FXMLLoader loader = new FXMLLoader(classObject.getResource(this.resourceName));
            loader.setController(controller);
            fxml = loader.load();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return fxml;
    }

    protected void loadFXLMInto(Class classObject, Initializable controller, Pane container){
        this.container = container;

        Parent fxml = this.loadFXML(classObject, controller);
        container.getChildren().setAll(fxml);
    }

}
