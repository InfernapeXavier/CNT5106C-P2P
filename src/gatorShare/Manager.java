package gatorShare;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class Manager {

    private Info info;
    private RandomAccessFile file;

    public Manager(int ID, Info info) throws FileNotFoundException {
        this.info = info;
        String dir = "peer_" + ID + "/";
        new File(dir);
        file = new RandomAccessFile(dir + info.getFilename(), "rw");
    }

    //MISSING: how to actually get and write the pieces. Will take care of later
}
