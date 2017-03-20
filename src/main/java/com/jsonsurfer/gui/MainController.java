package com.jsonsurfer.gui;

import com.jsonsurfer.gui.aufgaben.Console;
import com.jsonsurfer.gui.error.ErrorController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by Lukas on 09.03.17.
 */
public class MainController {
    @FXML private TextArea consoleTextArea;
    @FXML private Button clearConsoleButton;

    public Console output = new Console(this.consoleTextArea);
    public PrintStream console = new PrintStream(this.output);

    /**
     * Init console text area
     */
    public void init(){
        this.output = new Console(this.consoleTextArea);
        this.console =  new PrintStream(this.output);
    }

    /**
     * Clear console text
     */
    public void clearConsole(){
        output.clear();
    }

    public void showError(String error) {
        Platform.runLater(
                () -> {
                    Parent root;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/error/Error.fxml"));
                        root = loader.load();
                        ErrorController controller = (ErrorController) loader.getController();

                        Stage stage = new Stage();
                        stage.setTitle("Fehler!");
                        stage.setScene(new Scene(root));
                        controller.setErrorText(error);
                        stage.show();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
    }
}
