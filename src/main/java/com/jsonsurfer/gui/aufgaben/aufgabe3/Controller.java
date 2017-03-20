package com.jsonsurfer.gui.aufgaben.aufgabe3;

import network.InetAddressHandler;
import network.NTPHandler;
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
public class Controller extends MainController{
    @FXML private ComboBox serverCB;

    /**
     * initialize Tab Interface; load option in dropdown select
     *
     */
    public void initialize () throws IOException, ParseException {
        JSON jsonCon = new JSON();
        JSONObject jsonObject = jsonCon.getJSONObject("/data/data.json");
        JSONArray servers = (org.json.simple.JSONArray) jsonObject.get("ntp-timeservers");

        Iterator<String> iterator = servers.iterator();
        while (iterator.hasNext()) {
            String server = iterator.next();
            serverCB.getItems().add(server);
        }
        init();
    }

    /**
     * connect to NTF Server with selected domain
     * @throws IOException
     */
    public void connect() throws IOException {
        InetAddressHandler timeNTP = new InetAddressHandler(serverCB.getValue().toString());
        NTPHandler ntpservice = new NTPHandler(timeNTP,console);

        ntpservice.printStratum();
        ntpservice.printMessage();
        ntpservice.printDate();
    }
}
