package com.jsonsurfer.gui.error;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Created by Lukas on 20.03.17.
 */
public class ErrorController {

    @FXML private TextArea errorTextArea;

    public void setErrorText(String errorText){
        this.errorTextArea.setText(errorText);
    }

    public void closeWindow(){
        Stage stage = (Stage) errorTextArea.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
