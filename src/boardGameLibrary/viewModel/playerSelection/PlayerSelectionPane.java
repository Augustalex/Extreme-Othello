package boardGameLibrary.viewModel.playerSelection;

import boardGameLibrary.players.LocalPlayer;
import boardGameLibrary.players.Player;
import boardGameLibrary.viewModel.playerSelection.exceptions.NoSuchPlayerException;
import boardGameLibrary.viewModel.playerSelection.exceptions.NonUniquePlayerNameException;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by August on 2016-10-23.
 */
public class PlayerSelectionPane extends VBox {

    private int numberOfPlayers;

    private ObservableList<Player> playerOptions;
    private ObservableList<Player> occupied = FXCollections.observableArrayList();
    private ObservableList<SelectionConfirmationButton> confirmationButtons = FXCollections.observableArrayList();

    private BooleanProperty allPlayersSet = new SimpleBooleanProperty(false);

    public PlayerSelectionPane(Player[] playerOptions, int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;

        this.playerOptions = FXCollections.observableArrayList(playerOptions);

        for(int i = 0; i < numberOfPlayers; i++)
            addPlayerSelectionBox();

        //Add chosen player to occupied list when a player is chosen in a PlayerSelectionBox.
       /* for(PlayerSelectionBox box : this.selectionBoxes)
            box.getSelectionModel().selectedItemProperty().addListener(e -> this.occupied.add(box.getSelectionModel().getSelectedItem()));
*/
        for(SelectionConfirmationButton button : this.confirmationButtons){
            button.confirmedPlayerProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println(this.occupied.toString());

                if(this.occupied.contains(newValue)){
                    //TODO add message alert window
                    System.out.println("Player already selected.");



                }

                if(newValue != null)
                    this.occupied.add(newValue);
                else
                    this.occupied.remove(oldValue);
            });
        }


        //If all players have been chosen, activate allPlayersSet property.
        this.occupied.addListener((ListChangeListener<? super Player>) e -> {
            System.out.println("DIOS MIO!");
            System.out.println(this.occupied.size());
            if(this.occupied.size() == this.numberOfPlayers)
                this.allPlayersSet.set(true);
        });
    }

    private void addPlayerSelectionBox(){
        PlayerSelectionBox playerSelectionBox = new PlayerSelectionBox(this.playerOptions);
        SelectionConfirmationButton confirmationButton = new SelectionConfirmationButton(playerSelectionBox);

        PlayerSelectionRow selectionRow = new PlayerSelectionRow(playerSelectionBox, confirmationButton);
        this.getChildren().add(selectionRow);

        this.confirmationButtons.add(confirmationButton);
    }

    public BooleanProperty allPlayersSetProperty(){
        return this.allPlayersSet;
    }

    public Player[] getSelectedPlayers(){
        return this.occupied.toArray(new Player[this.numberOfPlayers]);
    }

    /*

    private Color nextColor(){
        Random random = new Random(System.currentTimeMillis());
        return Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    private Player getPlayerFromOptionName(String playerName){
        Player[] choice = this.playerOptions.parallelStream()
                .filter(player -> player.getName().equals(playerName))
                .toArray(Player[]::new);

        if(choice.length == 0)
            throw new NoSuchPlayerException();
        else if(choice.length > 1)
            throw new NonUniquePlayerNameException();
        else
            return choice[0];
    }
     */
}
