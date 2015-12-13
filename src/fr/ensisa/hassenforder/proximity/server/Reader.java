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

		case Protocol.GET_LOGIN :
			break;
		

		case Protocol.REQ_MOV :
			//readMov();
			break;
		/*case Protocol.REQ_RAD :
			readRad();
		case Protocol.REQ_PROPUPVIS :
			
		case Protocol.REQ_MODE :
			

		case Protocol.REQ_PROPUPLEV :*/
			
		default:
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
	
	public String readPreferenceName(){
		String pref = readString();
		return pref;
	}
	
	public int readPreferenceLevel(){
		int level = readInt();
		return level;
	}
	
	public boolean readPreferenceVis(){
		boolean vis = readBoolean();
		return vis;
	}
	
}
