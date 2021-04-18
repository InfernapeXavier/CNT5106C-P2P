package gatorShare;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class peerProcess implements Runnable{


    private int myID;
    private Info info;
    private MyLogger myLogger;
    private Bitfield bitfield;
    private int numberOfNeighbors;
    private Neighbors[] neighbors;
    private Manager manager;


    public peerProcess(int ID) throws IOException {

        this.myID = myID;
        this.info = new Info("Common.cfg", "PeerInfo.cfg");
        this.myLogger = new MyLogger(myID);
        this.bitfield = new Bitfield(info.getPieces());
        this.numberOfNeighbors = info.getNeighbors();
        neighbors = new Neighbors[numberOfNeighbors];
        this.manager = new Manager(this.myID, this.info);
    }

    //WIP
    public void initPeer(Neighbors neighbors) {
        Socket socket = neighbors.getUpload();
        //Handshake handshake = new Handshake();
        //handshake.setID(myID);
        //handshake.send(socket);
        //handshake.read(socket);



    }
    //WIP
    public void initServer(int index, ServerSocket serverDownload, ServerSocket serverUpload, ServerSocket serverHave) throws IOException {
        Socket downloadSocket = serverDownload.accept();
        Socket uploadSocket = serverUpload.accept();
        Socket haveSocket = serverHave.accept();

        //Handshake handshake = new Handshake();
    }

    //WIP
    public void start() {

    }

    public void run() {
        try {
            int index = -1;
            for (int i = 0; i < info.getNeighbors(); i++) {
                index++;
                if (myID == info.getIDs().get(i)) {
                    if (info.getDownloadCompleteStatus().get(i)) {
                        bitfield.activateAll();
                    }
                    break;
                }
                Socket download = new Socket(info.getHosts().get(i), info.getPort(i));
                Socket upload = new Socket(info.getHosts().get(i), info.getPort(i) + 1);
                Socket have = new Socket(info.getHosts().get(i), info.getPort(i) + 2);
                myLogger.connectsTo(info.getIDs().get(i));
                neighbors[i] = new Neighbors(info.getIDs().get(i), info.getPieces(), upload, download, have);
                initPeer(neighbors[i]);
            }
            ServerSocket serverDownload = null;
            ServerSocket serverUpload = null;
            ServerSocket serverHave = null;

            if(info.getNeighbors()-1 != index) {
                serverDownload = new ServerSocket(info.getPort(index));
                serverUpload = new ServerSocket(info.getPort(index) + 1);
                serverHave  = new ServerSocket(info.getPort(index) + 2);

                for (int i = index; i < info.getNeighbors() - 1; i++) {
                    initServer(i, serverDownload, serverUpload, serverHave);
                }
            }

            /*
            TO-DO: handle choking/unchoking, upload process, and whatnot
             */


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        peerProcess peer = new peerProcess(Integer.parseInt(args[0]));
        Thread thread = new Thread(peer);
        thread.start();
    }
}
