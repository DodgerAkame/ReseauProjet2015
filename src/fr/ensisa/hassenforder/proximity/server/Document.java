package fr.ensisa.hassenforder.proximity.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import fr.ensisa.hassenforder.proximity.model.Mode;
import fr.ensisa.hassenforder.proximity.model.Preference;
import fr.ensisa.hassenforder.proximity.model.User;

public class Document {

	private Map<String, User> users;
	
	public Document() {
		super();
		populate ();
	}

    private boolean populate () {
		users = new TreeMap<String, User>();
		User user = null;
		user = UserFactory.createAlfred();
		users.put(user.getName(), user);
		user = UserFactory.createGeorges();
		users.put(user.getName(), user);
		user = UserFactory.createHassen();
		users.put(user.getName(), user);
		user = UserFactory.createPamela();
		users.put(user.getName(), user);
		user = UserFactory.createSheila();
		users.put(user.getName(), user);
		return true;
	}

    public User doConnect(String name) {
    	User user = users.get(name);
    	if (user == null) return null;
    	return user;
    }

    public User doGetState(String name) {
    	User user = users.get(name);
    	if (user == null) return null;
    	return user;
    }

    private int distance (User other, User me) {
    	int dx = other.getX() - me.getX();
    	int dy = other.getY() - me.getY();
    	return dx*dx+dy*dy;
    }

    private boolean match (Map<String, Preference> os, Map<String, Preference> ms) {
		for (Preference m : ms.values()) {
			if (! m.isVisibility()) continue;
			Preference o = os.get(m.getName());
			if (o == null) continue;
			if (m.getLevel() == 0) return true;
			if (m.getLevel() > 0) {
				if (o.getLevel() < m.getLevel()) continue;
			}
			if (m.getLevel() < 0) {
				if (o.getLevel() > m.getLevel()) continue;
			}
			return true;
		}
		return false;
    }

    public List<User> doFind(String name) {
    	User me = null;
    	List<User> others = new ArrayList<User> (users.size());
    	for (User u : users.values()) {
    		if (u.getName().equals(name)) {
    			me = u;
    		} else {
    			others.add(u);
    		}
    	}
    	List<User> selected = new ArrayList<User> (others.size());
    	int d2 = me.getRadius() * me.getRadius();
    	for (User u : others) {
    		if (u.getMode() == Mode.HIDDEN) continue;
    		if (distance (u, me) > d2) continue;
    		if (! match(u.getPreferences(), me.getPreferences())) continue;
    		selected.add(u);
    	}
    	return selected;
    }

    public boolean doChangeMode (String name, Mode mode) {
    	User user = users.get(name);
    	if (user == null) return false;
    	user.setMode(mode);
    	return true;
    }

    public boolean doMove(String name, int x, int y) {
    	User user = users.get(name);
    	if (user == null) return false;
    	user.setX(x);
    	user.setY(y);
    	return true;
    }

    public boolean doChangeRadius(String name, int radius) {
    	User user = users.get(name);
    	if (user == null) return false;
    	user.setRadius(radius);
    	return true;
    }
	
    public boolean doChangePreferenceLevel(String name, String preference_name, int value) {
    	User user = users.get(name);
    	if (user == null) return false;
    	Preference preference = user.getPreferenceByName(preference_name);
    	if (preference == null) return false;
    	preference.setLevel(value);
    	return true;
    }

    public boolean doChangePreferenceVisibility(String name, String preference_name, boolean value) {
    	User user = users.get(name);
    	if (user == null) return false;
    	Preference preference = user.getPreferenceByName(preference_name);
    	if (preference == null) return false;
    	preference.setVisibility(value);
    	return true;
    }
	
}
