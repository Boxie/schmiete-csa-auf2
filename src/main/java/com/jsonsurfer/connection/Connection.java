package com.jsonsurfer.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Lukas on 09.03.17.
 */
public class Connection {


    public String getIP(String domain){
        InetAddress connection = getConnection(domain);
        if(connection != null) {
            return connection.getHostAddress();
        }
        return null;
    }


    public String getDomain (String ip){
        InetAddress connection = getConnection(ip);
        if(connection != null) {
            return connection.getHostName();
        }
        return null;
    }


    public InetAddress getConnection(String address){
        try {
            InetAddress addr = InetAddress.getByName(address);
            return addr;
        } catch (IOException e){

        }
        return null;
    }

    public String createSocket (String address, int port){
        try{
            InetAddress addr= InetAddress.getByName(address);
            Socket sock= new Socket(addr, port);
            sock.setSoTimeout(3000);
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String userInput;
            while ((userInput = in.readLine()) != null) {
                return userInput;
            }
            in.close();
            sock.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        return null;
    }
}
