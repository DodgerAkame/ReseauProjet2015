package fr.ensisa.hassenforder.proximity.server;


import java.io.InputStream;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Mode;

public class Reader extends BasicAbstractReader {


	public Reader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt ();
		switch (type) {
		case 0 :
			break;
		case Protocol.GET_LOGIN :
			readname();
		case Protocol.REQ_PREF :
			readname();
		case Protocol.REQ_MOV :
			readMov();
		case Protocol.REQ_RAD :
			readRad();
		case Protocol.GET_USER :
			break;
		case Protocol.REQ_PROPUPVIS :
			break;
		case Protocol.REQ_MODE :
			break;
		case Protocol.REQ_PROPUPLEV :
			break;
		}
	}

	public String readname() {
		String name = readString();
		return name;
		
	}

	public int[] readMov() {
		int x = readInt();
		int y = readInt();
		int t [] = {x,y};
		return t;
				
	}

	public int readRad(){
		int radius = readInt();
		return radius;
	}
	
}
