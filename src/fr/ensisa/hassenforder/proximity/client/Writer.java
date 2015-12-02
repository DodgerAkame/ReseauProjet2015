package fr.ensisa.hassenforder.proximity.client;

import java.io.OutputStream;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Mode;

public class Writer extends BasicAbstractWriter {

	/**
	 * Définition des échanges
	 */

	private final int GET_LOGIN = 1;
	private final int REQ_PREF = 2;
	private final int REQ_MOV = 3;
	private final int REQ_RAD = 4;
	private final int GET_USER = 5;
	private final int REQ_PROPUP = 6;

	public Writer(OutputStream outputStream) {
		super(outputStream);
	}

	public void send() {

	}

	public void requestLogin(String name) {
		writeInt(GET_LOGIN);
		writeString(name);
	}

	public void updateRadius(int rad) {
		writeInt(REQ_RAD);
		writeInt(rad);
	}

	public void updateMove(int x, int y) {
		writeInt(REQ_MOV);
		writeInt(x);
		writeInt(y);
	}

	public void searchNear(String name, int level) {
		writeInt(REQ_PREF);
		writeString(name);
		writeInt(level);
	}

	public void searchOther(int x, int y) {
		writeInt(GET_USER);
		writeInt(x);
		writeInt(y);
	}

	public void updatePreferenceVisibility(String hobby, boolean visibility) {
		writeInt(REQ_PROPUP);
		writeString(hobby);
		writeBoolean(visibility);
	}
        
        public void updatePreferenceLevel(String hobby, int level){
                writeInt(REQ_PROPUP);
                writeString(hobby);
                writeInt(level);
        }

}
