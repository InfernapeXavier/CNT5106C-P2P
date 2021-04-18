package gatorShare;

import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class Neighbors {
    private static int ID;
    private static Socket upload;
    private static Socket download;
    private static Socket have;
    private Bitfield bitfield;
    private volatile int progress;
    private AtomicInteger state;

    public Neighbors(int ID, int pieces, Socket upSocket, Socket downSocket, Socket haveSocket) {
        this.ID = ID;
        upload = upSocket;
        download = downSocket;
        have = haveSocket;
        progress = 0;

        state = new AtomicInteger(0);
        bitfield = new Bitfield(pieces);
    }

    public int getID() {return ID;}

    public void increase() {progress++;}

    public void clear() {progress = 0;}

    public int getProgress () {return progress;}

    public Socket getDownload() {return download;}

    public Socket getUpload() {return upload;}

    public Socket getHave() {return have;}

    public void setBitfield(Bitfield bitfield) {this.bitfield = bitfield;}

    public Bitfield getBitfield() {return bitfield;}

    public void activate(int bit) {bitfield.activate(bit);}

    public AtomicInteger getState() {return state;}

    public boolean finished() {return bitfield.finished();}
}
