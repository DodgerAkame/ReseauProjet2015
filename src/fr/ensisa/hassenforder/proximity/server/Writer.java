package fr.ensisa.hassenforder.proximity.server;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Preference;
import fr.ensisa.hassenforder.proximity.model.User;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) {
		super (outputStream);
	}

	public void error() {
		writeInt(Protocol.REP_KO);
		
	}

	public void estConnect(String name,int x, int y, int mode, int radius, Map<String, Preference> preferences) {
		writeInt(Protocol.REP_LOGIN);
		writeString(name);
		System.out.println("ttttt");
		System.out.println(name);
		writeInt(x);
		System.out.println(x);
		writeInt(y);
		System.out.println(y);
		writeInt(radius);
		System.out.println(radius);
		writeInt(mode);
		System.out.println(mode);
		
		int size = preferences.size();
		writeInt(size);
		for (int i =0; i<size ; i++){
			writeString(preferences.get(i).getName());
			writeInt(preferences.get(i).getLevel());
			writeBoolean(preferences.get(i).isVisibility());
			
		
		}
		
		
	}

	public void changeOK() {
		writeInt(Protocol.REP_OK);
		
	}

	public void sendFind() {
		
		
	}
	
	public void send(){
		super.send();
	}

	



}
