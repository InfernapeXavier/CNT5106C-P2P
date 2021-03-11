package gatorShare;

import java.io.Serializable;

public class Payload implements Serializable{
    
    private int length = 0;

    public Payload(int length){
        this.length = length;
    }

    public int getPayloadLength(){
        return this.length;
    }

}
