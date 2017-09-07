package view;

import javafx.scene.layout.Pane;
import model.Player;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * @author CS1331 TAs, jzeng45
 * @version 2.0
 */
public abstract class PlayerArea {

    private Pane pane;
    private Player player;

    private Label name;
    private Label chips;
    private Label indicator;
    private CardView cv1;
    private CardView cv2;

    /**
     * PlayerArea's constructor
     * @param  pane   The Pane where all UI elements will be added. The type of
     * pane is decided by subclasses
     * @param  player The Player who's information will be tracked
     */
    public PlayerArea(Pane pane, Player player) {
        this.pane = pane;
        this.player = player;

        name = new Label(player.toString());
        chips = new Label("Chips: "
            + String.valueOf(player.getMoney()));
        indicator = new Label("Out of Play");
        indicator.setVisible(false);
        cv1 = new CardView();
        cv2 = new CardView();

        VBox infoBox = new VBox();
        infoBox.getChildren().addAll(name, chips, indicator);
        this.pane.getChildren().addAll(cv1, cv2, infoBox);
    }

    /**
     * Getter for the Pane that contains all of the UI elements.
     * @return The Pane that contains all of the UI elements.
     */
    public Pane playerPane() {
        return pane;
    }

    /**
     * This method is called whenever an update to the UI needs to be made.
     * @param showDetails is true whenever the details of the front of the
     * cards are supposed to be shown false otherwise
     */
    public void update(boolean showDetails) {
        chips.setText("Chips: "
            + String.valueOf(player.getMoney()));

        if (player.getCard(0) != null) {
            cv1.setCard(player.getCard(0));
        }
        if (player.getCard(1) != null) {
            cv2.setCard(player.getCard(1));
        }

        if (player.getOutOfPlay()) {
            indicator.setVisible(true);
            cv1.hide();
            cv2.hide();
        } else {
            if (showDetails) {
                cv1.show();
                cv2.show();
            } else {
                cv1.hideDetails();
                cv2.hideDetails();
            }
        }
    }

}