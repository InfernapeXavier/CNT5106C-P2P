package gatorShare;

import java.io.*;
import java.text.*;
import java.util.*;

public class log {

    private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd, yyyy @ HH:mm:ss");
    private static Date date = new Date();
    private static BufferedWriter logger;

    public log(BufferedWriter logger) {
        this.logger = logger;
    }

    public static void connectsTo (int peerA, int peerB, BufferedWriter logger) {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(peerA);
        log.append(" makes a connection to peer ");
        log.append(peerB);
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

    public static void connectedFrom (int peerB, int peerA, BufferedWriter logger) {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(peerB);
        log.append(" is connected from peer ");
        log.append(peerA);
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

    public static void changedNeighbors(int peerA, int[] neighbors, BufferedWriter logger) {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(peerA);
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

    public static void optimisticUnchoking (int peerA, int peerB, BufferedWriter logger) {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(peerA);
        log.append(" has the optimistically unchoked neighbor ");
        log.append(peerB);
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

    public static void unchoked (int peerA, int peerB, BufferedWriter logger) {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(peerA);
        log.append(" is unchoked by peer ");
        log.append(peerB);
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

    public static void choked (int peerA, int peerB, BufferedWriter logger) {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(peerA);
        log.append(" is choked by peer ");
        log.append(peerB);
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

    public static void receiveHave (int peerA, int peerB, int piece, BufferedWriter logger) {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(peerA);
        log.append(" received the 'have' message from peer ");
        log.append(peerB);
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

    public static void receiveInterested (int peerA, int peerB, BufferedWriter logger) {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(peerA);
        log.append(" received the 'interested' message from peer ");
        log.append(peerB);
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

    public static void receiveNotIntereset (int peerA, int peerB, BufferedWriter logger) {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(peerA);
        log.append(" received the 'not interested' message from peer ");
        log.append(peerB);
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

    public static void downloading (int peerA, int peerB, int piece, int numPieces, BufferedWriter logger) {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(peerA);
        log.append(" has downloaded the piece");
        log.append(piece);
        log.append(" from ");
        log.append(peerB);
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

    public static void finishedDownloading (int peerA, BufferedWriter logger) {
        date = new Date();
        StringBuilder log = new StringBuilder();
        log.append(dateFormat.format(date));
        log.append(": Peer ");
        log.append(peerA);
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