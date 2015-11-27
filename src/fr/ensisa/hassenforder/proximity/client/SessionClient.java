package fr.ensisa.hassenforder.proximity.client;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Mode;
import fr.ensisa.hassenforder.proximity.model.User;

public class SessionClient {

	private Socket connection;
	
	public SessionClient (Socket connection) {
		this.connection = connection;
	}

	public User connect (String name) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public void disconnect () {
		connection = null;
	}

	public User getState(String name) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public List<User> findNear(String name) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public boolean changeMode (String name, Mode mode) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean move(String name, int x, int y) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean changeRadius(String name, int radius) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean changePreferenceLevel(String name, String preference, int value) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean changePreferenceVisibility(String name, String preference, boolean value) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return false;
		} catch (IOException e) {
			return false;
		}
	}

}
