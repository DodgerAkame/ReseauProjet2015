package fr.ensisa.hassenforder.proximity.server;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.User;

public class SessionServer {

	private Socket connection;
	private Document document;
	
	public SessionServer (Document document, Socket connection) {
		this.document = document;
		this.connection = connection;
	}

	public boolean operate() {
		try {
			Writer writer = new Writer (connection.getOutputStream());
			Reader reader = new Reader (connection.getInputStream());
			reader.receive ();
			int x,y,mode,radius;
			String name = reader.getString();
			
			
			
			switch (reader.getType ()) {
			case 0 : return false; // socket closed
			case 1 :
					if(document.doConnect(name)== NULL){
						writer.error(); // si pas de nom renvoie une erreur
					}else {
						x = document.doGetState(name).getX();
						y = document.doGetState(name).getY();
						radius = document.doGetState(name).getRadius();
						if (document.doGetState(name).getMode().equals("VISIBLE")){
							mode = 1;
													
						}else if (document.doGetState(name).getMode().equals("HIDDEN")){
							mode = 0;
						}else mode = 2;
						
						writer.estConnect(x,y,mode,radius,document.doGetState(name).getPreferences());
						
					}
						 
			default: return false; // connection jammed
			}
			writer.send ();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
