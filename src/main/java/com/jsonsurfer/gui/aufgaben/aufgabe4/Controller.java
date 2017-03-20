package com.jsonsurfer.gui.aufgaben.aufgabe4;

import network.SocketHandler;
import com.jsonsurfer.gui.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Created by Lukas on 09.03.17.
 */
public class Controller extends MainController {
    @FXML private TextField serverTextField;

    /**
     * Initialize tab interface
     */
    public void initialize (){
        init();
    }

    /**
     * connect to website and print html code to console
     */
    public void connect(){

        try {
            SocketHandler website = new SocketHandler(serverTextField.getText(), console);
            website.HTTPConnection();
            website.writeInput();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
