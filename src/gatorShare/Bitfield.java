package gatorShare;

public class Bitfield {

    private int pieces;
    private int downloaded;
    private boolean[] bitfield;
    private boolean finished;
    private int bytes;

    public Bitfield(int pieces) {
        this.pieces = pieces;
        downloaded = 0;
        bitfield = new boolean[pieces];
        deactivateAll();
    }

    public synchronized void setBits(byte[] bytes) {
        downloaded = 0;
        for (int i = 0; i < pieces; i++) {
            if (((1 << i%8) & bytes[i/8]) != 0) {
                downloaded++;
                bitfield[i] = true;
            } else {
                bitfield[i] = false;
            }
        }
    }

    public synchronized void initializeBytes(byte[] bytes, int number) {
        for (int i = 0; i < number; i++) {
            bytes[number] = 0;
        }
    }

    public synchronized byte[] asBytes() {
        if (pieces % 8 == 0) {
            bytes = pieces / 8;
        } else {
            bytes = pieces / 8 + 1;
        }
        byte[] asBytes = new byte[bytes];
        initializeBytes(asBytes, bytes);
        for (int i = 0; i < bytes; i++) {
            if (bitfield[i]) {
                asBytes[i/8] = (byte)((1 << i%8) | asBytes[i/8]);
            } else {
                asBytes[i/8] = (byte)~((1 << i%8) & asBytes[i/8]);
            }
        }
        return asBytes;
    }

    public synchronized void checkFinished() {
        if (pieces == downloaded) {
            setFinished();
        }
    }

    public synchronized boolean checkBitfield(int index) { return bitfield[index]; }

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
                setFinished();
            }
        }
    }

    public synchronized void deactivateAll() {
        for (int i = 0; i < pieces; i++) {
            if (bitfield[i]) { //== true
                bitfield[i] = false;
            }
        }
        finished = false;
    }

    public synchronized void activateAll() {
        for (int i = 0; i < pieces; i++) {
            bitfield[i] = true;
            downloaded++;
        }
        setFinished();
    }

    public synchronized boolean finished() {return finished;}

    public synchronized void setFinished() {finished = true;}

}
