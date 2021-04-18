package gatorShare;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class MyLogger {

    private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd, yyyy @ HH:mm:ss");
    private static Date date = new Date();
    private static BufferedWriter logger;
    private static int myID;
    private Logger log;


    public MyLogger (int myID) throws IOException {
        this.myID= myID;
        log = Logger.getLogger("Peer_" + myID);
        logger = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/Peer_" + myID + "/peer_" + myID + ".log"));
    }

    public static void connectsTo (int peerID) {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(myID);
        log.append(" makes a connection to peer ");
        log.append(peerID);
        log.append(".");
        try {
            System.out.println(log.toString());
            logger.write(log.toString());
            logger.newLine();
            logger.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void connectedFrom (int peerID) {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(peerID);
        log.append(" is connected from peer ");
        log.append(myID);
        log.append(".");
        try {
            System.out.println(log.toString());
            logger.write(log.toString());
            logger.newLine();
            logger.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changedNeighbors(int[] neighbors) {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(myID);
        log.append(" has the preferred neighbors ");
        for (int id : neighbors) {
            log.append(id).append(", ");
        }
        log.deleteCharAt(log.length() - 1);
        log.deleteCharAt(log.length() - 1);
        log.append(".");
        try {
            System.out.println(log.toString());
            logger.write(log.toString());
            logger.newLine();
            logger.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void optimisticUnchoking (int peerID) {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(myID);
        log.append(" has the optimistically unchoked neighbor ");
        log.append(peerID);
        log.append(".");
        try {
            System.out.println(log.toString());
            logger.write(log.toString());
            logger.newLine();
            logger.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unchoked (int peerID) {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(myID);
        log.append(" is unchoked by peer ");
        log.append(peerID);
        log.append(".");
        try {
            System.out.println(log.toString());
            logger.write(log.toString());
            logger.newLine();
            logger.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void choked (int peerID) {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(myID);
        log.append(" is choked by peer ");
        log.append(peerID);
        log.append(".");
        try {
            System.out.println(log.toString());
            logger.write(log.toString());
            logger.newLine();
            logger.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void receiveHave (int peerID, int piece) {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(myID);
        log.append(" received the 'have' message from peer ");
        log.append(peerID);
        log.append(" for the piece ");
        log.append(piece);
        log.append(".");
        try {
            System.out.println(log.toString());
            logger.write(log.toString());
            logger.newLine();
            logger.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void receiveInterested (int peerID) {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(myID);
        log.append(" received the 'interested' message from peer ");
        log.append(peerID);
        log.append(".");
        try {
            System.out.println(log.toString());
            logger.write(log.toString());
            logger.newLine();
            logger.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void receiveNotIntereset (int peerID) {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(myID);
        log.append(" received the 'not interested' message from peer ");
        log.append(peerID);
        log.append(".");
        try {
            System.out.println(log.toString());
            logger.write(log.toString());
            logger.newLine();
            logger.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void downloading (int peerID, int piece, int numPieces) {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(myID);
        log.append(" has downloaded the piece");
        log.append(piece);
        log.append(" from ");
        log.append(peerID);
        log.append(". Now the number of pieces it has is ");
        log.append(numPieces);
        log.append(".");
        try {
            System.out.println(log.toString());
            logger.write(log.toString());
            logger.newLine();
            logger.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void finishedDownloading () {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(myID);
        log.append(" has downloaded the complete file.");
        try {
            System.out.println(log.toString());
            logger.write(log.toString());
            logger.newLine();
            logger.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}