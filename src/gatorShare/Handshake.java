package gatorShare;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;


public class Handshake implements Serializable {

    private int ID;
    private Socket socket = null;
    private static final long serialVersionUID = -1482860868859618509L;
	private static String header = "P2PFILESHARINGPROJ";
	private final String peerMsgHeader = null;
    
    public Handshake(int myID, String header) {
    	this.ID = myID;
    	this.header = header;

    }
    
     /**
	 * @return the ID
	 */
    public int getID() {
		return ID;
	}
    
    /**
	 * @param //ID
	 *            the ID to set
	 */
	public void setID(int peerID) {
		this.ID = ID;
	}
    
    	/**
	 * @return the peerMsgHeader
	 */
	public String getPeerMsgHeader() {
		return peerMsgHeader;
	}

	/**
	 * @return the header
	 */
	public static String getHeader() {
		return header;
	}
    
    	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.append("[Header :").append(getHeader()).append("]\n").append("[ ID: ").append(this.ID).append("]")
				.toString();
	}

	public void SendHandShake(OutputStream out) throws IOException {
		ObjectOutputStream opStream = new ObjectOutputStream(out);
		opStream.writeObject(this);
		System.out.println("Sending handshake message to peer " + this.ID);
	}

	// return value could be changed to HandShakeMsg if header is also needed
	public int ReceiveHandShake(InputStream in) throws IOException {
		try {
			ObjectInputStream ipStream = new ObjectInputStream(in);
			Handshake Response = (Handshake) ipStream.readObject();
			return Response != null ? Response.ID : -1;
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		return -1;
	}
}
    
