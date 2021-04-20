package gatorShare;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

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

    public void initPeer(Neighbors neighbors) throws Exception {
        Socket socket = neighbors.getUpload();
        Handshake handshake = new Handshake(myID,"P2PFILESHARINGPROJECT");
        handshake.SendHandShake(socket.getOutputStream());
        handshake.ReceiveHandShake(socket.getInputStream());
        assert (myID == neighbors.getID()) : "[NOTICE] Handshake failed";
        myLogger.connectsTo(neighbors.getID());
        myLogger.connectedFrom(neighbors.getID());

        byte[] payload = neighbors.getBitfield().asBytes();
        Message bitfieldMessage = new Message(Message.messageType.BITFIELD, payload);
        bitfieldMessage.send(socket.getOutputStream());
        bitfieldMessage.receive(socket.getInputStream());
        Bitfield receivedBitfield = new Bitfield(info.getPieces());
        receivedBitfield.setBit(bitfieldMessage.getPayload());
        neighbors.setBitfield(receivedBitfield);

        Message interestMessage = new Message(Message.messageType.INTERESTED, null);
        if (bitfield.interested(receivedBitfield) == -1) {
            interestMessage.setType(Message.messageType.NOT_INTERESTED);
        }
        interestMessage.send(socket.getOutputStream());
        interestMessage.receive((socket.getInputStream()));
        if (interestMessage.getMessageType() == Message.messageType.INTERESTED) {
            myLogger.receiveInterested(neighbors.getID());
        } else {
            myLogger.receiveNotInterested(neighbors.getID());
        }

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
                    assert (info.getDownloadCompleteStatus().get(i));
                    bitfield.activateAll();
                }
                Socket download = new Socket(info.getHosts().get(i), info.getPort(i));
                Socket upload = new Socket(info.getHosts().get(i), info.getPort(i) + 1);
                Socket have = new Socket(info.getHosts().get(i), info.getPort(i) + 2);
                neighbors[i] = new Neighbors(info.getIDs().get(i), info.getPieces(), upload, download, have);
                initPeer(neighbors[i]);
            }

            assert (index != info.getNeighbors()-1);
            ServerSocket serverDownload = new ServerSocket(info.getPort(index));
            ServerSocket serverUpload = new ServerSocket(info.getPort(index) + 1);
            ServerSocket serverHave  = new ServerSocket(info.getPort(index) + 2);
            for (int i = index; i < info.getNeighbors() - 1; i++) {
                initServer(i, serverDownload, serverUpload, serverHave);
            }

            } catch (UnknownHostException unknownHostException) {
            unknownHostException.printStackTrace();


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
