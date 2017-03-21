package com.jsonsurfer.gui.aufgaben.aufgabe6;

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
    
    private ServerSocketHandler webServer;

    /**
     * initialize tab interface
     */
    public void initialize (){
        init();
    }

    /**
     * Start WebServer
     * @throws Exception
     */
    public void start() throws Exception{
    	this.webServer = new ServerSocketHandler("pages",console);
    	new Thread(){
        	public void run(){
        		try {
        			webServer.ExperimentalWebserver();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
                    showError(e.getMessage());
				}
        	}
        }.start();
    }

    /**
     * Close WebServer
     */
    public void stop() throws Exception{
        //TODO boolean server running?
        if(webServer.isRunning()) {
            webServer.endExperimentalWebserver();
        } else {
            console.print("Webserver not running\n");
        }
    }

}
