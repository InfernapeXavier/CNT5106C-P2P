package gatorShare;

import java.io.Serializable;

public class Message implements Serializable {

    enum messageType {
        CHOKE(0),
        UNCHOKE(1),
        INTERESTED(2),
        NOT_INTERESTED(3),
        HAVE(4),
        BITFIELD(5),
        REQUEST(6),
        PIECE(7);

        private int value;
        
        private messageType(int value) {
            this.value = value;
        }
    }


    private int length;
    private messageType type;
    private Payload messagePayload;

    public Message(messageType type) {
        this.type = type;
        if (messagePayload == null) {
            this.length = 0;
        } else {
            this.length = messagePayload.getPayloadLength();
        }
    
    }

    public int getMessageLength() {
        return this.length;
    }

    public messageType getMessageType() {
        return this.type;
    }

}
