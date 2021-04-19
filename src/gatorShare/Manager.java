package gatorShare;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Collections;

public class Manager {

    private Info info;
    private RandomAccessFile file;
    private ArrayList<Boolean> piecesReceived;

    public Manager(int ID, Info info) throws FileNotFoundException {
        this.info = info;
        String dir = "peer_" + ID + "/";
        new File(dir);
        this.file = new RandomAccessFile(dir + info.getFilename(), "rw");
        this.piecesReceived = new ArrayList<Boolean>(Collections.nCopies(info.getPieces(), false));
    }

    // MISSING: how to actually get and write the pieces. Will take care of later
    public void writePieceToFile(int pieceNumber, byte[] piecePayload) throws IOException {
        this.file.seek(pieceNumber * info.getPieceSize());
        this.write(piecePayload);
        this.piecesReceived[pieceNumber] = true;
    }

    public byte[] fetchPieceFromFile(int pieceNumber) throws IOException {
        this.file.seek(pieceNumber * info.getPieceSize());

        byte[] buffer;
        long remainingBytes = this.file.length() - (pieceNumber * info.getPieceSize());
        if (remainingBytes < info.getPieceSize())
            buffer = new byte[(int) remainingBytes];
        else
            buffer = new byte[info.getPieceSize()];
        this.file.read(buffer);
        this.file.close();
        return buffer;
    }

    public boolean checkFileStatus() {
        return new HashSet<Boolean>(this.piecesReceived).size() <= 1;
    }
}
