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
						
			reader.receive();
			
			System.out.println("yeah");
			System.out.println(reader.getType());
			System.out.println(Protocol.GET_LOGIN);
			switch (reader.getType()) {// probleme le processus s'arrête ici 
			//case 0:
			//	return false; // socket closed
			case Protocol.GET_LOGIN:
				System.out.println("je suis entré");
				int x,
				y,
				mode,
				radius;
				System.out.println("je suis toujours la");
				reader.readname();
				System.out.println("hahy");

				if (document.doConnect(reader.readname()) == null) {
					writer.error(); // si pas de nom renvoie une erreur
				} else {
					x = document.doGetState(reader.readname()).getX();
					y = document.doGetState(reader.readname()).getY();
					radius = document.doGetState(reader.readname()).getRadius();
					if (document.doGetState(reader.readname()).getMode().equals("VISIBLE")) {
						mode = 1;

					} else if (document.doGetState(reader.readname()).getMode().equals("HIDDEN")) {
						mode = 0;
					} else
						mode = 2;

					writer.estConnect(reader.readname(), x, y, mode, radius, document
							.doGetState(reader.readname()).getPreferences());
					writer.send();
				}

			/*case Protocol.REQ_RAD:
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
				}*/

				break;
			/*default:
				return false; // connection jammed */
			}
			writer.send();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
