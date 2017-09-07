package view;

import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import model.Card;
import model.Suit;
import model.CardValue;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

/**
 * @author CS1331 TAs, jzeng45
 * @version 2.0
 */
public class CardView extends StackPane {

    private static final int CARD_HEIGHT = 150;
    private static final int CARD_WIDTH = 107;
    private static final String BACK_LOCATION = "File:./src/main/res/"
        + "playing-card-back.png";
    private static final String FRONT_LOCATION = "File:./src/main/res/"
        + "playing-card-front.png";
    private static final String J_H_LOCATION = "File:./src/main/res/"
        + "j-heart.png";
    private static final String J_D_LOCATION = "File:./src/main/res/"
        + "j-diamond.png";
    private static final String J_S_LOCATION = "File:./src/main/res/"
        + "j-spade.png";
    private static final String J_C_LOCATION = "File:./src/main/res/"
        + "j-club.png";
    private static final String Q_H_LOCATION = "File:./src/main/res/"
        + "q-heart.png";
    private static final String Q_D_LOCATION = "File:./src/main/res/"
        + "q-diamond.png";
    private static final String Q_S_LOCATION = "File:./src/main/res/"
        + "q-spade.png";
    private static final String Q_C_LOCATION = "File:./src/main/res/"
        + "q-club.png";
    private static final String K_H_LOCATION = "File:./src/main/res/"
        + "k-heart.png";
    private static final String K_D_LOCATION = "File:./src/main/res/"
        + "k-diamond.png";
    private static final String K_S_LOCATION = "File:./src/main/res/"
        + "k-spade.png";
    private static final String K_C_LOCATION = "File:./src/main/res/"
        + "k-club.png";

    private static Image backIm;
    private static Image frontIm;
    private static Image jHeartIm;
    private static Image jDiamondIm;
    private static Image jSpadeIm;
    private static Image jClubIm;
    private static Image qHeartIm;
    private static Image qDiamondIm;
    private static Image qSpadeIm;
    private static Image qClubIm;
    private static Image kHeartIm;
    private static Image kDiamondIm;
    private static Image kSpadeIm;
    private static Image kClubIm;

    // statically loads Images
    static {
        backIm = new Image(BACK_LOCATION);
        frontIm = new Image(FRONT_LOCATION);
        jHeartIm = new Image(J_H_LOCATION);
        jDiamondIm = new Image(J_D_LOCATION);
        jSpadeIm = new Image(J_S_LOCATION);
        jClubIm = new Image(J_C_LOCATION);
        qHeartIm = new Image(Q_H_LOCATION);
        qDiamondIm = new Image(Q_D_LOCATION);
        qSpadeIm = new Image(Q_S_LOCATION);
        qClubIm = new Image(Q_C_LOCATION);
        kHeartIm = new Image(K_H_LOCATION);
        kDiamondIm = new Image(K_D_LOCATION);
        kSpadeIm = new Image(K_S_LOCATION);
        kClubIm = new Image(K_C_LOCATION);
    }

    // the background image of the card
    private ImageView background;
    // the top left label where card value is displayed
    private Label topL;
    // the bottom left label where card value is displayed
    private Label botR;
    // the middle label where card suit is displayed
    private Label mid;
    // the card where card info is found
    private Card card;

    /**
     * Constructor for CardView
     */
    public CardView() {
        background = new ImageView(frontIm);
        background.setFitHeight(CARD_HEIGHT);
        background.setFitWidth(CARD_WIDTH);
        topL = new Label();
        mid = new Label();
        botR = new Label();
        getChildren().addAll(background, topL, mid, botR);
    }

    /**
     * Gives the CardView a Card object which contains information on the Card
     * @param c The Card to display
     */
    public void setCard(Card c) {
        card = c;
        topL.setText(c.getCardValue().getStr());
        mid.setText(c.getSuit().getStr());
        botR.setText(c.getCardValue().getStr());
        if (c.getSuit() == Suit.HEART || c.getSuit() == Suit.DIAMOND) {
            mid.setTextFill(Color.GREY);
        } else {
            mid.setTextFill(Color.DARKORANGE);
        }
        mid.setFont(new Font(40));
        topL.setTranslateX(15 - CARD_WIDTH / 2);
        topL.setTranslateY(topL.getLayoutBounds().getHeight() / 2 + 5
            - CARD_HEIGHT / 2);
        botR.setTranslateX(CARD_WIDTH / 2 - 15);
        botR.setTranslateY(botR.getLayoutBounds().getHeight() / -2 - 5
            + CARD_HEIGHT / 2);
        setBackgroundImage(c);
    }

    /**
     * Shows the front of the Card
     */
    public void show() {
        if (card != null) {
            setBackgroundImage(card);
        }
        // background.setImage(frontIm);
        background.setVisible(true);
        topL.setVisible(true);
        mid.setVisible(true);
        botR.setVisible(true);
    }

    /**
     * Makes the card not display at all
     */
    public void hide() {
        background.setVisible(false);
        topL.setVisible(false);
        mid.setVisible(false);
        botR.setVisible(false);
    }

    /**
     * Shows the back of the card.
     */
    public void hideDetails() {
        background.setImage(backIm);
        background.setVisible(true);
        topL.setVisible(false);
        mid.setVisible(false);
        botR.setVisible(false);
    }

    /**
     * Set the background image of a card
     * @param c the card to display
     */
    public void setBackgroundImage(Card c) {
        if (c.getCardValue() == CardValue.JACK) {
            if (c.getSuit() == Suit.HEART) {
                background.setImage(jHeartIm);
            } else if (c.getSuit() == Suit.DIAMOND) {
                background.setImage(jDiamondIm);
            } else if (c.getSuit() == Suit.SPADE) {
                background.setImage(jSpadeIm);
            } else {
                background.setImage(jClubIm);
            }
        } else if (c.getCardValue() == CardValue.QUEEN) {
            if (c.getSuit() == Suit.HEART) {
                background.setImage(qHeartIm);
            } else if (c.getSuit() == Suit.DIAMOND) {
                background.setImage(qDiamondIm);
            } else if (c.getSuit() == Suit.SPADE) {
                background.setImage(qSpadeIm);
            } else {
                background.setImage(qClubIm);
            }
        } else if (c.getCardValue() == CardValue.KING) {
            if (c.getSuit() == Suit.HEART) {
                background.setImage(kHeartIm);
            } else if (c.getSuit() == Suit.DIAMOND) {
                background.setImage(kDiamondIm);
            } else if (c.getSuit() == Suit.SPADE) {
                background.setImage(kSpadeIm);
            } else {
                background.setImage(kClubIm);
            }
        } else {
            background.setImage(frontIm);
        }
    }
}