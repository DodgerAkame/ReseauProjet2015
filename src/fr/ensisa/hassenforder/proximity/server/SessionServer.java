package fr.ensisa.hassenforder.proximity.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Mode;
import fr.ensisa.hassenforder.proximity.model.Preference;

public class SessionServer {

	private Socket connection;
	private Document document;

	public SessionServer(Document document, Socket connection) {
		this.document = document;
		this.connection = connection;
	}

	public boolean operate() {
		try {
			Writer writer = new Writer(connection.getOutputStream());
			Reader reader = new Reader(connection.getInputStream());

			reader.receive();

			switch (reader.getType()) {
			case 0:
				return false; // socket closed
			case Protocol.GET_LOGIN:

				int x,
				y,
				mode = 0,
				radius;

				String name = reader.readname();

				if (document.doConnect(name) == null) {
					writer.error(); // si pas de nom renvoie une erreur
				} else {

					x = document.doGetState(name).getX();
					y = document.doGetState(name).getY();
					radius = document.doGetState(name).getRadius();
					if (document.doGetState(name).getMode() == Mode.VISIBLE) {

						mode = 1;

					} else if (document.doGetState(name).getMode() == Mode.HIDDEN) {
						mode = 0;
					} else if (document.doGetState(name).getMode() == Mode.OCCUPIED)
						mode = 2;

					// faire une map
					Map<String, Preference> buffer = document.doGetState(name)
							.getPreferences();

					writer.estConnect(name, x, y, mode, radius, buffer);
				}

				break;

			case Protocol.GET_USER:
				mode = 0;

				name = reader.readname();

				if (document.doConnect(name) == null) {
					writer.error(); // si pas de nom renvoie une erreur
				} else {

					x = document.doGetState(name).getX();
					y = document.doGetState(name).getY();
					radius = document.doGetState(name).getRadius();
					if (document.doGetState(name).getMode() == Mode.VISIBLE) {

						mode = 1;

					} else if (document.doGetState(name).getMode() == Mode.HIDDEN) {
						mode = 0;
					} else if (document.doGetState(name).getMode() == Mode.OCCUPIED)
						mode = 2;

					Map<String, Preference> buffer = document.doGetState(name)
							.getPreferences();

					writer.sendState(name, x, y, mode, radius, buffer);
				}

				break;

			case Protocol.REQ_RAD:
				name = reader.readname();
				System.out.println(name);
				int rad = reader.readRad();
				if (rad < 0) {
					writer.error();
				} else {
					document.doChangeRadius(name, rad);
					writer.changeOK();
				}

				break;

			case Protocol.REQ_MOV:
				name = reader.readname();
				int t[] = reader.readMov();
				if (t[0] < 0 || t[1] < 0) {
					writer.error();
				} else {
					document.doMove(name, t[0], t[1]);
					writer.changeOK();

				}
				break;

			case Protocol.REQ_PROPUPLEV:
				name = reader.readname();
				String preference = reader.readPreferenceName();
				int level = reader.readPreferenceLevel();

				document.doChangePreferenceLevel(name, preference, level);
				writer.changeOK();

				break;

			case Protocol.REQ_PROPUPVIS:
				name = reader.readname();
				preference = reader.readPreferenceName();
				boolean vis = reader.readPreferenceVis();

				document.doChangePreferenceVisibility(name, preference, vis);
				writer.changeOK();

				break;

			case Protocol.REQ_MODE:
				name = reader.readname();
				Mode modeenum = reader.readMode();

				document.doChangeMode(name, modeenum);
				writer.changeOK();

				break;

			case Protocol.REQ_PREF:
				name = reader.readname();
				if (name == null) {
					writer.error();
				} else {
					//document.doFind(name);
					writer.sendFind(document.doFind(name));
				}
				break;

			/*
			 * default: return false; // connection jammed
			 */
			}
			writer.send();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
