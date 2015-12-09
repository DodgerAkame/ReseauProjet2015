package fr.ensisa.hassenforder.proximity.client;

import java.io.OutputStream;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Mode;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) {
		super(outputStream);
	}

	public void send() {
		super.send();
	}

	public void requestLogin(String name) {
		writeInt(Protocol.GET_LOGIN);
		writeString(name);
		
	}

	public void updateRadius(int rad) {
		writeInt(Protocol.REQ_RAD);
		writeInt(rad);
	}

	public void updateMove(int x, int y) {
		writeInt(Protocol.REQ_MOV);
		writeInt(x);
		writeInt(y);
	}

	public void searchNear(String name, int level) {
		writeInt(Protocol.REQ_PREF);
		writeString(name);
		writeInt(level); // pour le find j'ai juste besoin du nom cf doFind()
	}

	/*
	 * pas possible avec les méthodes implémenté par le prof, c'est interne au
	 * client public void searchOther(int x, int y) {
	 * writeInt(Protocol.GET_USER); writeInt(x); writeInt(y); }
	 */
	public void updatePreferenceVisibility(String hobby, boolean visibility) {
		writeInt(Protocol.REQ_PROPUPVIS);
		writeString(hobby);
		writeBoolean(visibility);
	}

	public void updatePreferenceLevel(String hobby, int level) {
		writeInt(Protocol.REQ_PROPUPLEV);
		writeString(hobby);
		writeInt(level);
	}

	public void updateMode(Mode mode) {
		writeInt(Protocol.REQ_MODE);

		switch (mode) {
		case OCCUPIED:
			writeInt(2);
			break;
		case VISIBLE:
			writeInt(1);
			break;
		case HIDDEN:
			writeInt(0);
			break;
		default:
			break;
		}
	}

}
