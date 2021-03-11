package gatorShare;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Handshake {

    private int ID;
    private Socket socket = null;
    private String header;

    public Handshake(String message, int myID) {
        System.out.println(message.substring(28));
        System.out.println(myID);
    }
    
    public Handshake(String message, int myID, String header) {
    	this.ID = myID;
    	this.header = header;
    	System.out.println(message.substring(28));
        System.out.println(myID);
    }
}
