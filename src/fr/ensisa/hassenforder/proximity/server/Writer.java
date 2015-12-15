package fr.ensisa.hassenforder.proximity.server;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Mode;
import fr.ensisa.hassenforder.proximity.model.Preference;
import fr.ensisa.hassenforder.proximity.model.User;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) {
		super(outputStream);
	}

	public void error() {
		writeInt(Protocol.REP_KO);

	}

	public void estConnect(String name, int x, int y, int mode, int radius, Map<String, Preference> preferences) {
		writeInt(Protocol.REP_LOGIN);
		writeString(name);
		writeInt(x);
		writeInt(y);
		writeInt(radius);
		writeInt(mode);

		int size = preferences.size();
		writeInt(size);

		Iterator entries = preferences.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry entry = (Map.Entry) entries.next();
			String key = (String) entry.getKey();
			Preference value = (Preference) entry.getValue();

			writeString(key);
			writeInt(value.getLevel());
			writeBoolean(value.isVisibility());

		}

	}

	public void changeOK() {
		writeInt(Protocol.REP_OK);

	}

	public void sendFind(List<User> list) {
		writeInt(Protocol.REP_USERS); // tiens je te prémâche le boulot
		int size = list.size();
		writeInt(size);
		for (int i = 0; i < size; i++) {
			String name = list.get(i).getName();
			int x = list.get(i).getX();
			int y = list.get(i).getY();
			int radius = list.get(i).getRadius();
			int mode;
			switch (list.get(i).getMode()) {
			case VISIBLE:
				mode = 1;
				break;
			case HIDDEN:
				mode = 0;
				break;
			case OCCUPIED:
				mode = 2;
				break;
			default:
				mode = 2;
				break;

			}
			Map<String, Preference> buffer = list.get(i).getPreferences();

			writeString(name);
			writeInt(x);
			writeInt(y);
			writeInt(radius);
			writeInt(mode);

			writeInt(buffer.size());
			Iterator entries = buffer.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry entry = (Map.Entry) entries.next();
				String key = (String) entry.getKey();
				Preference value = (Preference) entry.getValue();

				writeString(key);
				writeInt(value.getLevel());
				writeBoolean(value.isVisibility());

			}
		}

	}

	public void send() {
		super.send();
	}

	public void sendState(String name, int x, int y, int mode, int radius, Map<String, Preference> preferences) {
		// TODO Auto-generated method stub
		writeInt(Protocol.REP_USER);
		writeString(name);
		writeInt(x);
		writeInt(y);
		writeInt(radius);
		writeInt(mode);

		int size = preferences.size();
		writeInt(size);

		Iterator entries = preferences.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry entry = (Map.Entry) entries.next();
			String key = (String) entry.getKey();
			Preference value = (Preference) entry.getValue();

			writeString(key);
			writeInt(value.getLevel());
			writeBoolean(value.isVisibility());

		}
	}

}
