package gatorShare;

public class Bitfield {

    private int pieces;
    private int downloaded;
    private boolean[] bitfield;
    private boolean finished;

    public  Bitfield(int pieces) {
        this.pieces = pieces;
        downloaded = 0;
        bitfield = new boolean[pieces];
        for (int i = 0; i < pieces; i++) {
            bitfield[i] = false;
        }
        finished = false;
    }

    public synchronized void setBit(byte[] bytes) {
        downloaded = 0;
        for (int i = 0; i < pieces; i++) {
            int currentByte = i/8;
            int currentBit = i%8;
            if (((currentBit >> 1) & bytes[currentByte]) != 0) {
                downloaded++;
                bitfield[i] = true;
            } else {
                bitfield[i] = false;
            }
            if (pieces == downloaded) {
                finished = true;
            }
        }
    }

    public synchronized int interested(Bitfield diffBitfield) {
        int notInterested = -1;
        for (int i = 0; i < pieces; i++) {
            if (diffBitfield.bitfield[i] && (!bitfield[i])) {
                return i;
            }
        }
        return notInterested;
    }

    public synchronized void activate(int bit) {
        if (!bitfield[bit]) {
            bitfield[bit] = true;
            downloaded++;
            if (downloaded == pieces) {
                finished = true;
            }
        }
    }

    public synchronized void activateAll() {
        for (int i = 0; i < pieces; i++) {
            bitfield[i] = true;
            downloaded++;
        }
        finished = true;
    }

    public synchronized boolean finished() {return finished;}
}
