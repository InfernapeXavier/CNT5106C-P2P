package gatorShare;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class Manager {

    private Info info;
    private RandomAccessFile file;
    private ArrayList<Boolean> piecesReceived;
    private static Hashtable<Integer, Boolean> requestedPieces = new Hashtable<Integer, Boolean>();


    public Manager(int ID, Info info) throws FileNotFoundException {
        this.info = info;
        String dir = "peer_" + ID + "/";
        new File(dir);
        this.file = new RandomAccessFile(dir + info.getFilename(), "rw");
        this.piecesReceived = new ArrayList<Boolean>(Collections.nCopies(info.getPieces(), false));
    }

    // MISSING: how to actually get and write the pieces. Will take care of later
    public void writePieceToFile(int pieceNumber, byte[] piecePayload) throws IOException {
        this.file.seek((long) pieceNumber * info.getPieceSize());
        this.file.write(piecePayload);
        this.piecesReceived.set(pieceNumber, true);
    }

    public byte[] fetchPieceFromFile(int pieceNumber) throws IOException {
        this.file.seek((long) pieceNumber * info.getPieceSize());

        byte[] buffer;
        long remainingBytes = this.file.length() - ((long) pieceNumber * info.getPieceSize());
        if (remainingBytes < info.getPieceSize())
            buffer = new byte[(int) remainingBytes];
        else
            buffer = new byte[info.getPieceSize()];
        this.file.read(buffer);
        this.file.close();
        return buffer;
    }

}
