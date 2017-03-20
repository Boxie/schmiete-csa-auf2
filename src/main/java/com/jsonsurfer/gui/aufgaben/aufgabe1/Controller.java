package com.jsonsurfer.gui.aufgaben.aufgabe1;

import com.jsonsurfer.connection.Connection;
import com.jsonsurfer.converter.JSON;
import com.jsonsurfer.gui.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Lukas on 09.03.17.
 */
public class Controller extends MainController{

    @FXML private TextField IPAddressTextField;
    @FXML private TextField DomainTextField;

    /**
     * initialize Tab Interface
     *
     */
    public void initialize (){
        init();
    }

    /**
     * Get IP Address based on Domain.
     *
     */
    public void getIP(){
        String requestIP = DomainTextField.getText();

        Connection conn = new Connection();

        String domain = conn.getIP(requestIP);

        output.writeln(requestIP + " --> " + domain);
    }

    /**
     * Get Domain based on IP Address
     */
    public void getDomain(){
        String requestDomain = IPAddressTextField.getText();
        Connection conn = new Connection();

        String ip = conn.getDomain(requestDomain);

        output.writeln(requestDomain + " --> " + ip);
    }

    /**
     * get all Domains based on IP Address List in data.json
     *
     */

    public void getJSONDomains() throws IOException, ParseException {
        // loop array
        JSON jsonCon = new JSON();
        JSONObject jsonObject = jsonCon.getJSONObject("data/data.json");
        JSONArray domains = (JSONArray) jsonObject.get("ipaddress");
        Connection conn = new Connection();

        Iterator<String> iterator = domains.iterator();
        while (iterator.hasNext()) {
            String ip = iterator.next();
            output.writeln(ip + " --> " + conn.getDomain(ip));
        }
    }

    /**
     * get all IP Addresses based on Domain List in data.json
     *
     */
    public void getJSONIPAddress() throws IOException, ParseException {
        // loop array
        JSON jsonCon = new JSON();
        JSONObject jsonObject = jsonCon.getJSONObject("data/data.json");
        JSONArray domains = (JSONArray) jsonObject.get("domains");
        Connection conn = new Connection();

        Iterator<String> iterator = domains.iterator();
        while (iterator.hasNext()) {
            String domain = iterator.next();
            output.writeln(domain + " --> " + conn.getIP(domain));
        }
    }

}
