package fr.ensisa.hassenforder.proximity.server;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Preference;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) {
		super(outputStream);
	}

	public void error() {
		writeInt(Protocol.REP_KO);

	}

	public void estConnect(String name, int x, int y, int mode, int radius,
			Map<String, Preference> preferences) {
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

	public void sendFind() {

	}

	public void send() {
		super.send();
	}

}
