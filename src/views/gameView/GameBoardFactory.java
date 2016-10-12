/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.gameView;


import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


/**
 *
 * @author S132063
 */
public class GameBoardFactory {

    public static GridPane createBoard(Pane contianer, int maxSize){
        GridPane pane = new GridPane();
        pane.setId("gameBoard");

        //pane.prefWidthProperty().bind(contianer.widthProperty());
        //pane.prefHeightProperty().bind(pane.widthProperty());
        //ColumnConstraints column = new ColumnConstraints();
        //column.setPercentWidth(100);
        //pane.getColumnConstraints().add(column);

        for (int i = 0; i < maxSize; i++)
            for (int j = 0; j < maxSize; j++)
                pane.add(new Cell(), j, i);
        
        return pane;
    }
    
}
