package fr.ensisa.hassenforder.proximity.client;

import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Mode;
import fr.ensisa.hassenforder.proximity.model.Preference;
import fr.ensisa.hassenforder.proximity.model.User;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author hassenforder
 */
public class Document {

	private Socket socket;
    private SessionClient session;
    private User me;
    private List<User> others;

    private int count = 0;
    public void passThrough (String id) {
    	++count;
    	System.out.println("Pass Through "+id+" count "+count);
    }

    public boolean isConnected() {
		if (socket == null) return false;
		if (session == null) return false;
		return true;
    }

    public boolean doConnect(String name) {
    	passThrough ("doConnect");
		try {
			if (socket != null) return false;
			if (session != null) return false;
			socket = new Socket("localhost", Protocol.PROXIMITY_PORT_ID);
			session = new SessionClient(socket);
	    	me = session.connect(name);
	    	return true;
		} catch (Exception e) {
			socket = null;
			session = null;
			return false;
		}
    }

    public boolean doDisconnect() {
    	passThrough ("doDisconnect");
		if (socket == null) return false;
		if (session == null) return false;
		session.disconnect();
		session = null;
		try {
			socket.close();
		} catch (IOException e) {
		}
		socket = null;
		return true;
    }

    public User doGetState() {
    	passThrough("getState");
    	me = session.getState (me.getName());
    	return me;
    }

    public List<User> doFind() {
    	passThrough("find");
    	others = session.findNear(me.getName());
    	return others;
    }

    public boolean doChangeMode (Mode mode) {
    	passThrough("visibility changed "+mode);
    	return session.changeMode (me.getName(), mode);
    }

    public boolean doMove(int x, int y) {
    	passThrough("move "+x+", "+y);
    	return session.move(me.getName(), x, y);
    }

    public boolean doChangeRadius(int radius) {
    	passThrough("radius "+radius);
    	return session.changeRadius(me.getName(), radius);
    }

    public boolean doChangePreferenceLevel(int id, int row, int value) {
    	passThrough("ID "+id+" level changed to "+value+" for "+row);
    	if (id != -1) return false;
    	if (me == null) return false;
    	Preference preference = me.getPreferenceByPosition(row);
    	if (preference == null) return false;
    	boolean result = session.changePreferenceLevel (me.getName(), preference.getName(), value);
    	if (result) preference.setLevel(value);
    	return result;
    }

    public boolean doChangePreferenceVisibility(int id, int row, boolean value) {
    	passThrough("ID "+id+" visibility changed to "+value+" for "+row);
    	if (id != -1) return false;
    	if (me == null) return false;
    	Preference preference = me.getPreferenceByPosition(row);
    	if (preference == null) return false;
    	boolean result = session.changePreferenceVisibility (me.getName(), preference.getName(), value);
    	if (result) preference.setVisibility(value);
    	return result;
    }

    public User getMe() {
        return me;
    }

    public List<User> getOthers() {
        return others;
    }

    public User getOther(int id) {
        getOthers();
        if (others == null) return null;
        if (id >= others.size()) return null;
        return others.get(id);
    }

    public User getUser(int id) {
        switch (id) {
        case -2 : return null;
        case -1 : return getMe();
        default : return getOther (id);
        }
    }

    public User getUser(int x, int y) {
        {
            User u = getMe();
            if (u.getX()/10 == x/10 && u.getY()/10 == y/10) return u;
        }
        for (User u : getOthers()) {
            if (u.getX()/10 == x/10 && u.getY()/10 == y/10) return u;
        }
        return null;
    }

    public int getId(User user) {
        if (user == null) return -2;
        if (user == me) return -1;
        int p = 0;
        for (User u : getOthers()) {
            if (user == u) return p;
            ++p;
        }
        return -2;
    }
}
