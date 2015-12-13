package fr.ensisa.hassenforder.proximity.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Mode;
import fr.ensisa.hassenforder.proximity.model.Preference;
import fr.ensisa.hassenforder.proximity.model.User;

public class SessionClient {

	private Socket connection;
	private User me;

	public SessionClient(Socket connection) {
		this.connection = connection;
	}

	public User connect(String name) {
		try {

			Writer writer = new Writer(connection.getOutputStream());
			writer.requestLogin(name);
			writer.send();

			Reader reader = new Reader(connection.getInputStream());
			reader.receive();

			if (reader.getType() == Protocol.REP_KO) {
				throw new IOException("Connection aborted");
			}

			if (reader.getType() == Protocol.REP_LOGIN) {
				User user = null;
				Map<String, Preference> pref = null;

				user = reader.readLogin();
				pref = reader.readPreferences();

				Iterator entries = pref.entrySet().iterator();

				while (entries.hasNext()) {

					String bonjour = entries.next().toString(); // Crée un
																// string
																// contenant la
																// préférence

					StringTokenizer st = new StringTokenizer(bonjour, "="); // Je
																			// transforme
																			// la
																			// chaîne
																			// en
																			// tokens

					String hobby = "";
					int level = 0;
					boolean vis = false;

					Preference buffer = new Preference(hobby, level, vis);

					buffer = pref.get(st.nextToken()); // Je recupere la
														// préférence en
														// utilisant comme clé
														// le 1er token

					user.addPreference(buffer);

				}
				this.me = user;
				return user;
			}
			return null;

		} catch (IOException e) {

			return null;

		}
	}

	public void disconnect() {
		connection = null;
	}

	public User getState(String name) {
		try {

			Writer writer = new Writer(connection.getOutputStream());
			writer.getState(name);
			writer.send();

			Reader reader = new Reader(connection.getInputStream());
			reader.receive();

			if (reader.getType() == Protocol.REP_KO)
				throw new IOException("not yet implemented");

			if (reader.getType() == Protocol.REP_USER) {
				User user = null;
				Map<String, Preference> pref = null;

				user = reader.readUser();
				pref = reader.readPreferences();

				Iterator entries = pref.entrySet().iterator();

				while (entries.hasNext()) {

					String bonjour = entries.next().toString();

					StringTokenizer st = new StringTokenizer(bonjour, "=");

					String hobby = "";
					int level = 0;
					boolean vis = false;

					Preference buffer = new Preference(hobby, level, vis);

					buffer = pref.get(st.nextToken());

					user.addPreference(buffer);

				}
				this.me = user;
				return user;

			}
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public List<User> findNear(String name) { // *************
		try {

			Writer writer = new Writer(connection.getOutputStream());
			writer.searchNear(name);
			writer.send();

			Reader reader = new Reader(connection.getInputStream());
			reader.receive();

			if (reader.getType() == Protocol.REP_KO)
				throw new IOException("Request aborted");

			if (reader.getType() == Protocol.REP_USERS) {
				List<User> users = null;
				// Réception à faire

				return users;
			}

			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public boolean changeMode(String name, Mode mode) { // **************
		try {

			Writer writer = new Writer(connection.getOutputStream());
			writer.updateMode(mode);
			writer.send();

			Reader reader = new Reader(connection.getInputStream());
			reader.receive();

			if (reader.getType() == Protocol.REP_KO)
				throw new IOException("Request aborted");
			if (reader.getType() == Protocol.REP_USER) {
				// Y'a un truc là

				return true;
			}
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean move(String name, int x, int y) {
		try {

			Writer writer = new Writer(connection.getOutputStream());
			writer.updateMove(name, x, y);
			writer.send();

			Reader reader = new Reader(connection.getInputStream());
			reader.receive();

			if (reader.getType() == Protocol.REP_KO)
				throw new IOException("Request move aborted");

			if (reader.getType() == Protocol.REP_OK) {
				this.me.setX(x);
				this.me.setY(y);

				return true;
			}

			return false;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean changeRadius(String name, int radius) {
		try {

			Writer writer = new Writer(connection.getOutputStream());
			writer.updateRadius(name, radius);
			writer.send();

			Reader reader = new Reader(connection.getInputStream());
			reader.receive();

			if (reader.getType() == Protocol.REP_KO)
				throw new IOException("Request move aborted");

			if (reader.getType() == Protocol.REP_OK) {
				this.me.setRadius(radius);

				return true;
			}

			return false;
		} catch (IOException e) {
			return false;

		}
	}

	public boolean changePreferenceLevel(String name, String preference,
			int value) {
		try {

			Writer writer = new Writer(connection.getOutputStream());
			writer.updatePreferenceLevel(preference, value);
			writer.send();

			Reader reader = new Reader(connection.getInputStream());
			reader.receive();

			if (reader.getType() == Protocol.REP_OK)
				return true;
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean changePreferenceVisibility(String name, String preference,
			boolean value) {
		try {
			if (true)
				throw new IOException("not yet implemented");

			Writer writer = new Writer(connection.getOutputStream());
			writer.updatePreferenceVisibility(preference, value);
			writer.send();

			Reader reader = new Reader(connection.getInputStream());
			reader.receive();

			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
