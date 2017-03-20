package com.jsonsurfer.gui.aufgaben.aufgabe2;

import com.jsonsurfer.connection.Connection;
import com.jsonsurfer.converter.JSON;
import com.jsonsurfer.gui.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Lukas on 09.03.17.
 */
public class Controller extends MainController {

    @FXML private ComboBox ServerCB;

    /**
     * Initialize tab interface; load option in dropdown menu from data.json
     *
     */
    public void initialize () throws IOException, ParseException {
        init();
        JSON jsonCon = new JSON();
        JSONObject jsonObject = jsonCon.getJSONObject("/data/data.json");
        JSONArray servers = (org.json.simple.JSONArray) jsonObject.get("timeservers");

        Iterator<String> iterator = servers.iterator();
        while (iterator.hasNext()) {
            String server = iterator.next();
            ServerCB.getItems().add(server);
        }
    }

    /**
     * Connect to TimeServer
     * Port: 13
     */
    public void connectToServer(){
        Connection conn = new Connection();
        if(ServerCB.getValue() != null) {
            output.writeln("Try to connect to "+ ServerCB.getValue());
            String response = conn.createSocket(ServerCB.getValue().toString(),13);
            output.writeln(response);
        }
    }

}
