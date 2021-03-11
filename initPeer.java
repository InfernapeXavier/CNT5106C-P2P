package gatorShare;

import java.net.Socket;

public class initPeer {

    private Socket socket = null;

    public initPeer(String host, int port) {
        try {
            socket = new Socket(host, port);
            System.out.println("[TEST]: Client requesting connection...");
            System.out.println("Socket: " + socket);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
