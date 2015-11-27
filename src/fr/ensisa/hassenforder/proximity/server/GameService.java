package fr.ensisa.hassenforder.proximity.server;

import java.io.IOException;
import java.net.Socket;

public class GameService extends Thread {

	private Socket connection;
	private Document document;
	private int id;
	private int count;
	
	public GameService(Document document, Socket connection, int id) {
		this.document = document;
		this.connection = connection;
		this.id = id;
		this.count = 0;
	}

	public void run() {
		System.out.println("Start service "+id);
		SessionServer session = new SessionServer (document, connection);
		while (true) {
			System.out.println("Service "+id+" serving request "+count);
			if (! session.operate())
				break;
			System.out.println("Service "+id+" request served "+count);
			++count;
		}
		try {
			if (connection != null) connection.close();
		} catch (IOException e) {
		}
		System.out.println("Stop service "+id);
	}

}
