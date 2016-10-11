package views;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import views.exceptions.ViewNotLoadedException;
import views.mainMenu.MainViewController;

import java.net.URL;

/**
 * Created by August on 2016-10-10.
 */
public abstract class FXMLViewController implements ViewController{

    private String resourceName;

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
        Parent fxml = this.loadFXML(classObject, controller);
        container.getChildren().setAll(fxml);
    }

}
