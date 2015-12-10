package fr.ensisa.hassenforder.proximity.server;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.User;

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
			
			System.out.println("hihi");
			System.out.println(reader.getType());
			
			reader.receive();// probleme le processus s'arrête ici 
			
			System.out.println("yeah");

			switch (reader.getType()) {
			//case 0:
			//	return false; // socket closed
			case Protocol.GET_LOGIN:

				int x,
				y,
				mode,
				radius;
				String name = reader.readname();
				System.out.println(name);
				System.out.println("hahy");

				if (document.doConnect(name) == null) {
					writer.error(); // si pas de nom renvoie une erreur
				} else {
					x = document.doGetState(name).getX();
					y = document.doGetState(name).getY();
					radius = document.doGetState(name).getRadius();
					if (document.doGetState(name).getMode().equals("VISIBLE")) {
						mode = 1;

					} else if (document.doGetState(name).getMode()
							.equals("HIDDEN")) {
						mode = 0;
					} else
						mode = 2;

					writer.estConnect(name, x, y, mode, radius, document
							.doGetState(name).getPreferences());
					writer.send();
				}

			case Protocol.REQ_RAD:
				String name1 = reader.readname();
				System.out.println(name1);
				int rad = reader.readRad();
				if (rad < 0) {
					writer.error();
				} else {
					document.doChangeRadius(name1, rad);
					writer.changeOK();
				}

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

			case Protocol.REQ_PREF:
				String name2 = reader.readname();
				if (name2 == null) {
					writer.error();
				} else {
					document.doFind(name2);
					writer.sendFind();
				}

				break;
			default:
				return false; // connection jammed
			}
			writer.send();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
