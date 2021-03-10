package gatorShare;

import java.io.Serializable;

public class message implements Serializable {

    enum messageType {
        CHOKE(0),
        UNCHOKE(1),
        INTERESTED(2),
        NOT_INTERESTED(3),
        HAVE(4),
        BITFIELD(5),
        REQUEST(6),
        PIECE(7)
    }

    private int length;
    private messageType type;
    // Need to dfine payload
    // private payload payload;

    public message(messageType type) {
        this.type = type;
        // Set length by checking payload
        // this.length = length;
    
    }

    public int getMessageLength(int length) {
        return this.msgLength;
    }


}
