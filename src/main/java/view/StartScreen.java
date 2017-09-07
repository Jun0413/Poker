package view;

import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

/**
 * @author CS1331 TAs, jzeng45
 * @version 2.0
 */
public class StartScreen extends StackPane {

    // Path to the image file for the background
    private static final String BACK_LOCATION = "File:./src/main/res"
        + "/poker-game-background.png";

    /**
     * StartScreen's constructor
     * @param cont The PokerGame to interact with
     */
    public StartScreen(PokerGame cont) {

        Image image = new Image(BACK_LOCATION);
        ImageView iv = new ImageView();
        iv.setImage(image);
        iv.setFitHeight(650);
        iv.setFitWidth(1000);
        iv.setPreserveRatio(true);

        // iv.fitWidthProperty().bind(this.widthProperty());
        // iv.fitHeightProperty().bind(this.heightProperty());

        Button startButton = new Button("Start New Game");
        StackPane.setAlignment(startButton, Pos.BOTTOM_LEFT);
        startButton.setTranslateX(50);
        startButton.setTranslateY(this.getHeight() - 50);
        startButton.setOnAction(e -> {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("New Game");
                dialog.setHeaderText("Confirmation");
                dialog.setContentText("Enter your name:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    TextInputDialog aDialog = new TextInputDialog();
                    aDialog.setTitle("New Game");
                    aDialog.setHeaderText("Game Setting");
                    aDialog.setContentText("Set initial chips:");
                    Optional<String> aResult = aDialog.showAndWait();
                    if (aResult.isPresent()) {
                        cont.startGame(result.get(),
                                Integer.parseInt(aResult.get()));
                    }
                }
            });

        this.getChildren().addAll(iv, startButton);
    }

}