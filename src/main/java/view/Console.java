package view;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;


/**
 * @author CS1331 TAs, jzeng45
 * @version 2.0
 */
public class Console extends ScrollPane {

    private static Console instance;

    private Label csLbl;

    /**
     * Console's constructor. Set's the static instance variable.
     */
    public Console() {
        instance = this;

        csLbl = new Label();
        csLbl.setWrapText(true);
        instance.setContent(csLbl);
        instance.setMinHeight(155);
        instance.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        instance.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    }

    /**
     * Add's text to the top of the console. (Doesn't get rid of
     * text that is already there!)
     * @param newText is the text to add to the top of the console
     */
    public void addText(String newText) {
        csLbl.setText(newText + "\n" + csLbl.getText());
        this.setVvalue(0);
    }

    /**
     * Clears the console of any text
     */
    public void clear() {
        csLbl.setText("");
    }

    /**
     * Static method that adds a message into the current
     * {@value  instance}
     * @param message The message to add
     */
    public static void putMessage(String message) {
        instance.addText(message);
    }

    /**
     * Clears the console of the current {@value instance}
     */
    public static void clearLog() {
        instance.clear();
    }
}