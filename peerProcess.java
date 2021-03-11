package gatorShare;

import java.io.*;
import java.net.*;
import java.util.*;


public class peerProcess extends Thread{

    private final int ID;
    private Info info;
    private final int port;
    private final String host;
    private final boolean downloadStatus;
    private Handshake handshake;
    private Socket socket;
    private static File dir;


    public peerProcess (int ID) throws  FileNotFoundException {
        this.ID = ID;
        this.info = new Info("src/Common.cfg", "src/PeerInfo.cfg");
        this.host = info.getHost(ID);
        this.port = info.getPort(ID);
        this.downloadStatus= info.getDownloadComplete(ID);

    }

    public void initializeServer() {
        new initService(port);
    }

    public void initializePeer(String host, int port)  {
        try {
            new initPeer(host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //I'm not sure if the handshake message should contain the ID of the receiver or the ID of the sender.
    //It would probably make sense if it were the receiver's ID, as we could then easily compare if we got the
    //right one, but I'm hesitant about this.
    public void shakehands(peerProcess peer) {
        String handshakeMessage = "P2PFILESHARINGPROJ0000000000" + peer.ID;
        System.out.println(handshakeMessage);
        this.handshake = new Handshake(handshakeMessage, ID);
    }

    public void establishConnection(peerProcess peer) {
        try {
            shakehands(peer);
            logger(peer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logger(peerProcess peer) {
        try {
            dir = new File("Peer_" + ID);
            if (!dir.exists()) {
                dir.mkdir();
            }
            BufferedWriter myLog = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/Peer_" + ID + "/peer_" + ID + ".log"));
            log.connectsTo(ID, peer.ID, myLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        //Testing
        peerProcess client = new peerProcess(Integer.parseInt(args[0]));
        System.out.println("[TEST]: This peer ID is " + client.ID +".\nLocated at " + client.host
                + "\nListening to port " + client.port + "\nDownload status is " + client.downloadStatus);

        //More testing
        peerProcess peer = new peerProcess(1002);
        System.out.println("[TEST]: This peer ID is " + peer.ID +".\nLocated at " + peer.host
                + "\nListening to port " + peer.port + "\nDownload status is " + peer.downloadStatus);

        //Handshake part or something, still trying to figure it out
        //client.initializeServer();
        //client.initialize(client.host, client.port);
        //peer.initializePeer(peer.host, peer.port);
        client.establishConnection(peer);
    }
}
