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
		// TODO Auto-generated method stub
		
	}

	public void estConnect(int x, int y, int mode, int radius, Map<String, Preference> preferences) {
		// TODO Auto-generated method stub
		int size = preferences.size();
		for (int i =0; i<size ; i++){
			preferences.get(i).getName();
			preferences.get(i).getLevel();
			preferences.get(i).isVisibility();
			
		}
		
	}



}
