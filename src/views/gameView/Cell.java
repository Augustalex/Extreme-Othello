/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.gameView;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
/**
 *
 * @author S132063
 */
public class Cell extends StackPane {
    // Indicate the row and column of this cell in the board

    public Cell() {
      this.setId("gameBoardCell");
      this.setOnMouseClicked(e ->handleMouseClick()); 
    }

    private void handleMouseClick() {
        /**throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates. */ 
        System.out.println("lalala");
        
    }
    void markCell(Shape shape){
        
        
        this.widthProperty().addListener((e)->{
            shape.prefWidth(this.widthProperty().get());
        });
        
        this.heightProperty().addListener((e)->{
            shape.prefHeight(this.heightProperty().get());
        });
    }
}