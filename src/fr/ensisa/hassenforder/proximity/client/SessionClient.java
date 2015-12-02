package fr.ensisa.hassenforder.proximity.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Mode;
import fr.ensisa.hassenforder.proximity.model.User;
import java.util.Iterator;

public class SessionClient {

	private Socket connection;
	
	public SessionClient (Socket connection) {
		this.connection = connection;
	}

	public User connect (String name) {
		try {
			if (!connection.isConnected()) throw new IOException ("No Socket");
			
			//Faire une socket
			Writer writer = new Writer(connection.getOutputStream());
			writer.requestLogin(name);
                        writer.send();
                        
			Reader reader = new Reader(connection.getInputStream());
			reader.receive();
			
			User user;
                        
                        //Utiliser un iterateur pour parcourir la liste
			
                        
                        
			return user;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void disconnect () {
            connection = null;
	}

	public User getState(String name) {
		try {
			if (!connection.isConnected()) throw new IOException ("not yet implemented");
                        User user;
                        
                        Writer writer = new Writer(connection.getOutputStream());
                        
                        Reader reader = new Reader(connection.getInputStream());
			return user;
		} catch (IOException e) {
			return null;
		}
	}

	public List<User> findNear(String name) {
		try {
			if (true) throw new IOException ("not yet implemented");
                        
                        List<User> users;
                        
                        
			return users;
		} catch (IOException e) {
			return null;
		}
	}

	public boolean changeMode (String name, Mode mode) {
		try {
			if (true) throw new IOException ("not yet implemented");
			
                        Writer writer = new Writer(connection.getOutputStream());
                        writer.;
                        writer.send();
                        
                        Reader reader = new Reader(connection.getInputStream());
                        reader.receive();
                        
                        return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean move(String name, int x, int y) {
		try {
			if (true) throw new IOException ("not yet implemented");
                        
                        Writer writer = new Writer(connection.getOutputStream());
                        writer.updateMove(x, y);
                        writer.send();
                        
                        Reader reader = new Reader(connection.getInputStream());
                        reader.receive();
                        
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean changeRadius(String name, int radius) {
		try {
			if (true) throw new IOException ("not yet implemented");
                        
                        Writer writer = new Writer(connection.getOutputStream());
                        writer.updateRadius(radius);
                        writer.send();
                        
                        Reader reader = new Reader(connection.getInputStream());
                        reader.receive();
                        
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean changePreferenceLevel(String name, String preference, int value) {
		try {
			if (true) throw new IOException ("not yet implemented");
                        
                        Writer writer = new Writer(connection.getOutputStream());
                        writer.updatePreferenceLevel(preference, value);
                        writer.send();
                        
                        Reader reader = new Reader(connection.getInputStream());
                        reader.receive();
                        
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean changePreferenceVisibility(String name, String preference, boolean value) {
		try {
			if (true) throw new IOException ("not yet implemented");
                        
                        
                        
                        Writer writer = new Writer(connection.getOutputStream());
                        writer.;
                        writer.send();
                        
                        Reader reader = new Reader(connection.getInputStream());
                        reader.receive();
                        
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
