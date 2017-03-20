package com.jsonsurfer;
/**
 * Created by Lukas on 09.03.17.
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * Start program
     *
     * @param args no args set
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Start gui frontend
     *
     * @param stage starting stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Main.fxml"));

        Scene scene = new Scene(root);
        stage.setMinWidth(510);
        stage.setMinHeight(400);
        stage.setTitle("�bung 2");
        stage.setScene(scene);
        stage.show();
    }
}