package boardGameLibrary.views.javaFxViews.gameView;

import boardGameLibrary.boardGame.board.GameBoard;
import boardGameLibrary.boardGame.match.GameMatch;
import boardGameLibrary.boardGame.move.CalculatedMove;
import boardGameLibrary.boardGame.move.Move;
import boardGameLibrary.eventWrappers.CellChangeEvent;
import boardGameLibrary.eventWrappers.CellClickEvent;
import boardGameLibrary.boardGame.pawn.Pawn;
import boardGameLibrary.viewModel.ViewDimensionBinder;
import boardGameLibrary.viewModel.gameBoard.cell.Cell;
import boardGameLibrary.viewModel.gameBoard.GameBoardFactory;
import boardGameLibrary.views.javaFxViews.FXMLViewController;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.awt.Dimension;
import java.awt.Point;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import boardGameLibrary.viewModel.gameBoard.cellMarker.CellMarker;

/**
 * Created by August on 2016-09-29.
 */
public class GameViewController extends FXMLViewController{
    
    private static final String fxmlFileName = "GameView.fxml";

    private GameMatch match;

    @FXML
    private HBox gameBoardContainer;
    
    public GameViewController(Pane container, GameMatch match){
        super(container, GameViewController.fxmlFileName);
        this.match = match;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Game board boundaries
        Dimension boundaries = this.match.getBoardMoveMaker().getGameBoard().getBoundaries();

        //Creating boardGame board
        Pane gameBoard = GameBoardFactory.createBoard(gameBoardContainer, boundaries.width);

        gameBoardContainer.getChildren().setAll(gameBoard);

        //Fixing boardGame board dimension properties
        gameBoardContainer.widthProperty().addListener((e) -> ViewDimensionBinder.squareBindTo(gameBoard, gameBoardContainer));
        gameBoardContainer.heightProperty().addListener((e) -> ViewDimensionBinder.squareBindTo(gameBoard, gameBoardContainer));

        ViewDimensionBinder.squareBindTo(gameBoard, gameBoardContainer);

        // Doing boardGame logic here, hellooo, gosh.
        listenToGameBoardView(gameBoard, this.match.getBoardMoveMaker().getGameBoard(), match.cellClickProperty());
        bindViewToGameBoardModel(this.match.getBoardMoveMaker().getGameBoard(), gameBoard);
        bindDisplayAvailableMoves(gameBoard, this.match.legalMovesProperty(), boundaries);

        match.run();
    }

    public void bindViewToGameBoardModel(GameBoard board, Pane boardView){
        Dimension boundaries = board.getBoundaries();
        ObservableList<Node> viewCells = boardView.getChildren();

        board.getCellChangeObserver().addListener((ListChangeListener.Change<? extends CellChangeEvent> c) -> {

            for(CellChangeEvent cellChange : c.getList()){

                Point position = cellChange.getPosition();

                System.out.println("FLIPPED PAWN AT " + position);

                Pawn pawn = board.getPawn(position);
                Cell cell = (Cell) viewCells.get((position.y * boundaries.width) + position.x);

                Shape shape = pawn.getDisplayModel().getShape();
                shape.setFill(pawn.getDisplayModel().getPaint());

                cell.markCell(CellMarker.createCellMarker(shape));

            }

        });
    }

    private void listenToGameBoardView(Pane boardView, GameBoard board, ObjectProperty<CellClickEvent> cellClickProperty){
        ObservableList<Node> viewCells = boardView.getChildren();

        Dimension boundaries = board.getBoundaries();

        for(int y = 0; y < boundaries.height; y++){
            for(int x = 0; x < boundaries.width; x++){
                final Point position = new Point(x, y);
                viewCells.get((y*boundaries.width) + x).setOnMouseClicked(e -> {
                    System.out.println("CLICK: " + position.x + ", " + position.y);
                    cellClickProperty.set(new CellClickEvent(position));
                });

                Cell cell = (Cell) viewCells.get((y*boundaries.width) + x);
                Shape shape = board.getPawn(position).getDisplayModel().getShape();
                shape.setFill(board.getPawn(position).getDisplayModel().getPaint());
                cell.markCell(CellMarker.createCellMarker(shape));
            }
        }

    }

    private void bindDisplayAvailableMoves(Pane boardView, ObjectProperty<ArrayList<CalculatedMove>> availableMoves, Dimension boardBoundaries){
        availableMoves.addListener(e -> {

            boardView.getChildren().stream().filter(node -> node instanceof Cell).forEach(node -> {
                Cell cell = (Cell) node;
                cell.removeHighlight();
            });

            double highestScore = 0;
            for(CalculatedMove move : availableMoves.get())
                if(move.getScore() > highestScore)
                    highestScore = move.getScore();

            for(CalculatedMove move : availableMoves.get()){
                Point position = new Point(move.getActions()[0].getX(), move.getActions()[0].getY());

                Cell cell;
                Node node = boardView.getChildren().get((position.y * boardBoundaries.width) + position.x);

                if(!(node instanceof Cell))
                    return;

                cell = (Cell) node;

                Circle highlight = new Circle();

                highlight.setFill(Color.TRANSPARENT);
                highlight.setStroke(Color.YELLOWGREEN);

                highlight.setOpacity((double)move.getScore() / highestScore);

                highlight.setStrokeWidth(3);
                CellMarker marker = CellMarker.createCellMarker(highlight);

                cell.highlightCell(marker);
            }
        });
    }

}

