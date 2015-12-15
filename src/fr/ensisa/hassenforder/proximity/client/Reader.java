package fr.ensisa.hassenforder.proximity.client;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Mode;
import fr.ensisa.hassenforder.proximity.model.Preference;
import fr.ensisa.hassenforder.proximity.model.User;

public class Reader extends BasicAbstractReader {

	public Reader(InputStream inputStream) {
		super(inputStream);
	}

	public void receive() {
		type = readInt();
	}

	public User readLogin() {
		String name = readString();
		int x = readInt();
		int y = readInt();
		int radius = readInt();
		int modeInt = readInt();
		Mode mode = null;
		if (modeInt == 1) {
			mode = Mode.VISIBLE;
		} else if (modeInt == 0) {
			mode = Mode.HIDDEN;
		} else {
			mode = Mode.OCCUPIED;
		}

		User user = new User(name, x, y, radius, mode);

		return user;
	}

	public User readUser() {
		String name = readString();
		int x = readInt();
		int y = readInt();
		int radius = readInt();
		int modeInt = readInt();
		Mode mode = null;

		if (modeInt == 1) {
			mode = Mode.VISIBLE;
		} else if (modeInt == 0) {
			mode = Mode.HIDDEN;
		} else {
			mode = Mode.OCCUPIED;
		}

		User user = new User(name, x, y, radius, mode);

		return user;
	}


	public Map<String, Preference> readPreferences() {
		Map<String, Preference> buffer = new HashMap<String, Preference>();
		String string;
		int level;
		boolean vis;

		int size = readInt();

		for (int i = 0; i < size; i++) {
			string = readString();
			level = readInt();
			vis = readBoolean();
			Preference pref = new Preference(string, level, vis);

			buffer.put(string, pref);
		}

		return buffer;
	}

	public int readSize() {
		int size = readInt();
		return size;
	}

	/*
	 * public User readFind(){ int x,y,radius,mode; String name; boolean vis;
	 * Mode modeEnum;
	 * 
	 * 
	 * 
	 * User user = new User(name, x, y, radius, mode);
	 * 
	 * 
	 * return user; }
	 */

}