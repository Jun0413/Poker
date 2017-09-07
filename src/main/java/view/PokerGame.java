package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import viewcontroller.PokerGameController;
import javafx.scene.layout.VBox;
import viewcontroller.GameState;


/**
 * @author CS1331 TAs, jzeng45
 * @version 2.0
 */
public class PokerGame extends Application {

    public static Stage primaryStage;

    private GameScreen gameScreen;
    private ControlPane contPane;
    private Console console;
    private PokerGameController cont;

    /**
     * this method is called upon running/launching the application
     * this method should display a scene on the stage
     * @param ps The primary Stage
     */
    public void start(Stage ps) {
        primaryStage = ps;

        StartScreen startScreen = new StartScreen(this);
        Scene scene = new Scene(startScreen);
        ps.setScene(scene);
        ps.setTitle("Extreme Poker");
        ps.show();
    }

    /**
     * Starts the Game
     * This is called by StartScreen whenever it is done and the GameScreen,
     * ControlPane, and Console should be displayed
     * @param name The name of the human player
     */
    public void startGame(String name) {
        cont = new PokerGameController(this, name);

        VBox gameBox = new VBox();
        gameBox.setPrefWidth(1000);
        gameBox.setPrefHeight(700);
        gameScreen = new GameScreen(cont);
        contPane = new ControlPane(cont);
        console = new Console();
        gameBox.getChildren().addAll(gameScreen, contPane, console);
        Scene scene = new Scene(gameBox);
        primaryStage.setScene(scene);

        cont.start();
    }

    /**
     * Starts the Game
     * This is an overloaded startGame in the new version to set the chips
     * @param name  The name of the human player
     * @param chips Initial chips set by human
     */
    public void startGame(String name, int chips) {
        cont = new PokerGameController(this, name, chips);

        VBox gameBox = new VBox();
        gameBox.setPrefWidth(1000);
        gameBox.setPrefHeight(700);
        gameScreen = new GameScreen(cont);
        contPane = new ControlPane(cont);
        console = new Console();
        gameBox.getChildren().addAll(gameScreen, contPane, console);
        Scene scene = new Scene(gameBox);
        primaryStage.setScene(scene);

        cont.start();
    }

    /**
     * This is called by PokerGameController whenever updates are made. You
     * must handle updating the UI here.
     */
    public void updatesMade() {
        if (cont.getState() == GameState.DONE) {
            gameScreen.endOfRound();
            contPane.endOfRound();
        } else {
            gameScreen.updatesMade();
            if (cont.getState() == GameState.HUMAN_BET) {
                contPane.playerTurn();
            } else if (cont.getState() == GameState.AI_BET) {
                contPane.disableButtons();
            }
        }
    }

    /**
     * This is the main method that launches the javafx application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}