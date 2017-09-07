package view;

import javafx.scene.layout.HBox;
import model.Board;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

/**
 * @author CS1331 TAs, jzeng45
 * @version 2.0
 */
public class BoardArea {

    private Board board;

    private HBox pane;
    private CardView[] cards = new CardView[5];
    // private CardView cv1;
    // private CardView cv2;
    // private CardView cv3;
    // private CardView cv4;
    // private CardView cv5;

    private Label pot;

    /**
     * Constructor for the board's display
     * @param  board The Board object that contains data associated with the
     * board
     */
    public BoardArea(Board board) {
        this.board = board;

        pane = new HBox();
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(20);

        // cv1 = new CardView();
        // cv2 = new CardView();
        // cv3 = new CardView();
        // cv4 = new CardView();
        // cv5 = new CardView();

        cards[0] = new CardView();
        cards[1] = new CardView();
        cards[2] = new CardView();
        cards[3] = new CardView();
        cards[4] = new CardView();

        pot = new Label("Pot: " + board.getPot());

        pane.getChildren().addAll(cards[0], cards[1], cards[2], cards[3],
            cards[4], pot);
    }

    /**
     * Getter for the HBox that all UI elements are on
     * @return the HBox that all Board UI elements are on
     */
    public HBox getPane() {
        return pane;
    }

    /**
     * Updates UI elements
     */
    public void update() {

        for (int i = 0; i < board.getNumCards(); i++) {
            cards[i].setCard(board.getCards()[i]);
        }
        for (int i = 0; i < 5 - board.getNumCards(); i++) {
            cards[i].hide();
        }
        for (int i = 0; i < board.getNumCards(); i++) {
            cards[i].show();
        }
        // if (board.getTableCard(0) != null) {
        //     cv1.setCard(board.getTableCard(0));
        //     cv1.show();
        // } else {
        //     cv1.hide();
        // }
        // if (board.getTableCard(1) != null) {
        //     cv2.setCard(board.getTableCard(1));
        //     cv2.show();
        // } else {
        //     cv2.hide();
        // }
        // if (board.getTableCard(2) != null) {
        //     cv3.setCard(board.getTableCard(2));
        //     cv3.show();
        // } else {
        //     cv3.hide();
        // }
        // if (board.getTableCard(3) != null) {
        //     cv4.setCard(board.getTableCard(3));
        //     cv4.show();
        // } else {
        //     cv4.hide();
        // }
        // if (board.getTableCard(4) != null) {
        //     cv5.setCard(board.getTableCard(4));
        //     cv5.show();
        // } else {
        //     cv5.hide();
        // }
        pot.setText("Pot: " + board.getPot());
    }

}