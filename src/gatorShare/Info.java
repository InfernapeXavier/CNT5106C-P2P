package gatorShare;

import java.io.*;
import java.util.*;

public class Info {

    private final int numberOfNeighbors;
    private final int unchokingInterval;
    private final int optimisticUnchoking;
    private final String filename;
    private final int filesize;
    private final int pieceSize;
    private final ArrayList<Integer> IDs;
    private final ArrayList<String> host;
    private final ArrayList<Integer> port;
    private final ArrayList<Boolean> downloadComplete;
    private int peers;


    public Info(String common, String peerInfo) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader(common));
        String neighbors = in.nextLine();
        String[] split = neighbors.split(" ");
        this.numberOfNeighbors = Integer.parseInt(split[1].trim());

        String unchoke = in.nextLine();
        String[] split1 = unchoke.split(" ");
        this.unchokingInterval = Integer.parseInt(split1[1].trim());

        String optimistic = in.nextLine();
        String[] split2 = optimistic.split(" ");
        this.optimisticUnchoking = Integer.parseInt(split2[1].trim());

        String file = in.nextLine();
        String[] split3 = file.split(" ");
        this.filename = split3[1].trim();

        String size = in.nextLine();
        String[] split4 = size.split(" ");
        this.filesize = Integer.parseInt(split4[1].trim());

        String piece = in.nextLine();
        String[] split5 = piece.split(" ");
        this.pieceSize = Integer.parseInt(split5[1].trim());

        in.close();
        peers = 0;
        Scanner inPeer = new Scanner(new FileReader(peerInfo));
        IDs = new ArrayList<Integer>();
        host = new ArrayList<String>();
        port = new ArrayList<Integer>();
        downloadComplete = new ArrayList<Boolean>();
        while (inPeer.hasNextLine()) {
            String data = inPeer.nextLine();
            String[] fields = data.split(" ");
            this.IDs.add(Integer.parseInt(fields[0].trim()));
            this.host.add(fields[1].trim());
            this.port.add(Integer.parseInt(fields[2].trim()));
            if (fields[3].trim().equals("0")) {
                this.downloadComplete.add(false);
            } else {
                this.downloadComplete.add(true);
            }
        }
        peers += 1;
        inPeer.close();
    }

    public int getNeighbors() {
        return numberOfNeighbors;
    }

    public int getPeers() {
        return peers;
    }

    public int getUnchokingInterval() {
        return unchokingInterval;
    }

    public int getOptimisticUnchoking() {
        return optimisticUnchoking;
    }

    public String getFilename() {
        return filename;
    }

    public int getFilesize() {
        return filesize;
    }

    public int getPieceSize() {
        return pieceSize;
    }

    public int getPieces() {
        return (int) Math.ceil((double) filesize / pieceSize);
    }

    public ArrayList<Integer> getIDs() {
        return  IDs;
    }

    public String getHost(int ID) {
        return host.get(ID-1001);
    }

    public ArrayList<String> getHosts() {
        return host;
    }

    public int getPort(int ID) {
        return port.get(ID);
    }

    public ArrayList<Integer> getPorts() {
        return port;
    }

    public boolean getDownloadComplete(int ID) {
        return downloadComplete.get(ID-1001);
    }

    public ArrayList<Boolean> getDownloadCompleteStatus() {
        return downloadComplete;
    }
}