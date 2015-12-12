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
					System.out.println(document.doGetState(name).getMode());
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

			/*
			 * case Protocol.REQ_RAD: String name1 = reader.readname();
			 * System.out.println(name1); int rad = reader.readRad(); if (rad <
			 * 0) { writer.error(); } else { document.doChangeRadius(name1,
			 * rad); writer.changeOK(); }
			 */

			case Protocol.REQ_MOV:
				String name0 = reader.readname();
				System.out.println(name0);
				int t[] = reader.readMov();
				if (t[0] < 0 || t[1] < 0) {
					writer.error();
				} else {
					document.doMove(name0, t[0], t[1]);
					writer.changeOK();
				}
				break;

			/*
			 * case Protocol.REQ_PREF: String name2 = reader.readname(); if
			 * (name2 == null) { writer.error(); } else {
			 * document.doFind(name2); writer.sendFind(); }
			 */

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
