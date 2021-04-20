package gatorShare;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

    public void setType(messageType type) {
        this.type = type;
    }


    private int length;
    private messageType type;
    private byte[] messagePayload;

    public Message(messageType type, byte[] messagePayload) {
        this.type = type;
        if (messagePayload == null) {
            this.length = 0;
        } else {
            this.length = messagePayload.length;
        }

    }

    public int getMessageLength() {
        return this.length;
    }

    public messageType getMessageType() {
        return this.type;
    }

    public byte[] getPayload() {return this.messagePayload; }

    public void send (OutputStream out) throws IOException {
        out.write((byte)(messagePayload.length + 4));
        out.write((byte)type.value);
        out.write(messagePayload);
        out.flush();
    }

    public void receive (InputStream in) throws IOException {
        byte[] length = new byte[4];
        byte[] type = new byte[4];
        byte[] payload;

        for (int i = 0; i < 4; i++) {
            i += in.read(length, i, 4-i);
        }
        int len = toInt(length);

        for (int i = 0; i < 4; i++) {
            i += in.read(type, i, 4-i);
        }
        int tpe = toInt(type);
        if (len <= 4) {
            payload = null;
        } else {
            payload = new byte[len-4];
        }
        for (int i = 0; i < len-4; i++) {
            i += in.read(payload, i, len-4-i);
        }


    }

    private int toInt(byte[] bytes){
        return ((bytes[0] & 0xFF) << 24) |
                ((bytes[1] & 0xFF) << 16) |
                ((bytes[2] & 0xFF) << 8) |
                ((bytes[3] & 0xFF) << 0);
    }

}