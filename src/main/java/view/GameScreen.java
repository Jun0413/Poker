package view;

import javafx.scene.layout.BorderPane;
import viewcontroller.PokerGameController;
import javafx.geometry.Insets;

/**
 * @author CS1331 TAs, jzeng45
 * @version 2.0
 */
public class GameScreen extends BorderPane {

    private BoardArea bA;
    private HorizontalPlayer topPA;
    private VerticalPlayer rightPA;
    private HorizontalPlayer bottomPA;
    private VerticalPlayer leftPA;

    /**
     * GameScreen's constructor
     * @param controller The PokerGameController to interact with
     */
    public GameScreen(PokerGameController controller) {
        bA = new BoardArea(controller.getBoard());
        topPA = new HorizontalPlayer(controller.getTopPlayer());
        rightPA = new VerticalPlayer(controller.getRightPlayer());
        bottomPA = new HorizontalPlayer(controller.getBottomPlayer());
        leftPA = new VerticalPlayer(controller.getLeftPlayer());

        this.setCenter(bA.getPane());
        this.setTop(topPA.playerPane());
        this.setRight(rightPA.playerPane());
        this.setBottom(bottomPA.playerPane());
        this.setLeft(leftPA.playerPane());

        BorderPane.setMargin(bA.getPane(), new Insets(10, 50, 10, 50));
        this.setMinHeight(520);
        this.setMinWidth(1080);

        this.updatesMade();
    }

    /**
     * This method is called whenever normal updates to the UI need to be made.
     */
    public void updatesMade() {
        topPA.update(false);
        bottomPA.update(true);
        leftPA.update(false);
        rightPA.update(false);
        bA.update();
    }

    /**
     * This method is called whenever a round of poker ends
     */
    public void endOfRound() {
        topPA.update(true);
        bottomPA.update(true);
        leftPA.update(true);
        rightPA.update(true);
        bA.update();
    }

}