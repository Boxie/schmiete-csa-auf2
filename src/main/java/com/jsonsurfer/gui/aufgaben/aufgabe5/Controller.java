package com.jsonsurfer.gui.aufgaben.aufgabe5;

import network.ServerSocketHandler;
import com.jsonsurfer.gui.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by Lukas on 09.03.17.
 */
public class Controller extends MainController {
    @FXML private TextField messageTextField;

    private ServerSocketHandler echo = null;

    /**
     * initialize tab interface
     */
    public void initialize (){
        init();
    }

    /**
     * start Server on port 7
     */
    public void start(){
        new Thread(){
        	public void run(){
        		try {

        			echo = new ServerSocketHandler(7,messageTextField.getText(),console);
                    echo.startTelnet();
                    console.print("Started TelNet on Port 7\n");
        		} catch (IOException e) {
                    showError(e.getMessage());
        			e.printStackTrace();
        		}
        	}
        }.start();
    }

    public void stop(){
        //TODO boolean server running?
        try {
            echo.endTelnet();
            console.print("Stopped TelNet\n");
        } catch (IOException e) {
            showError(e.getMessage());
            e.printStackTrace();
        }
    }
}
