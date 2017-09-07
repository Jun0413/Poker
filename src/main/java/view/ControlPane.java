package view;

import javafx.scene.layout.HBox;
import viewcontroller.PokerGameController;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.media.AudioClip;

/**
 * @author CS1331 TAs, jzeng45
 * @version 2.0
 */
public class ControlPane extends HBox {

    private PokerGameController cont;

    private TextField input;
    private Button raiseButton;
    private Button callButton;
    private Button foldButton;
    private Button newRoundButton;
    private AudioClip clickSound;

    /**
     * Constructor for ControlPane
     * @param  cont The PokerGameController to interact with
     */
    public ControlPane(PokerGameController cont) {
        this.cont = cont;

        raiseButton = new Button("Raise");
        callButton = new Button("Call");
        foldButton = new Button("Fold");
        newRoundButton = new Button("Start New Round");
        input = new TextField();

        raiseButton.setDisable(true);
        callButton.setDisable(true);
        foldButton.setDisable(true);
        newRoundButton.setDisable(true);
        newRoundButton.setVisible(false);

        clickSound = new AudioClip("File:./src/main/res"
                + "/click_one.wav");

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(input, raiseButton,
                callButton, foldButton, newRoundButton);

        raiseButton.setOnAction(e -> {
                clickSound.play();
                try {
                    if (!input.getText().trim().isEmpty()) {
                        cont.humanBet(Integer.parseInt(input.getText()));
                    }
                } catch (NumberFormatException t) {
                    System.out.println(t.getMessage());
                }
            });

        callButton.setOnAction(e -> {
                clickSound.play();
                cont.humanCall();
            });

        foldButton.setOnAction(e -> {
                clickSound.play();
                cont.humanFold();
            });

        newRoundButton.setOnAction(e -> {
                clickSound.play();
                cont.startNewPokerHand();
            });
    }

    /**
     * Called whenever it becomes the player's turn again
     */
    public void playerTurn() {
        raiseButton.setDisable(false);
        callButton.setDisable(false);
        foldButton.setDisable(false);
        newRoundButton.setDisable(false);
        newRoundButton.setVisible(false);
    }

    /**
     * Method called when the round ends.
     */
    public void endOfRound() {
        newRoundButton.setVisible(true);
        newRoundButton.setDisable(false);
        raiseButton.setDisable(true);
        callButton.setDisable(true);
        foldButton.setDisable(true);
    }

    /**
     * Method that disables all the buttons.
     */
    public void disableButtons() {
        raiseButton.setDisable(true);
        callButton.setDisable(true);
        foldButton.setDisable(true);
        newRoundButton.setDisable(true);
    }

}