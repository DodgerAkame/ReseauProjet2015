package fr.ensisa.hassenforder.proximity.server;

import java.net.ServerSocket;
import java.net.Socket;

import fr.ensisa.hassenforder.network.Protocol;


public class Main {

	private ServerSocket server = null;
	private Document document = null;
	
	public void server () {
		try {
			int count = 0;
			server = new ServerSocket (Protocol.PROXIMITY_PORT_ID);
			document = new Document ();
			while (true) {
				Socket connection = server.accept();
				GameService service = new GameService (document, connection, ++count);
				service.start ();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main m = new Main ();
		m.server();
	}

}
