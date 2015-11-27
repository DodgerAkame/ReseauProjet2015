package fr.ensisa.hassenforder.proximity.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author hassenforder
 */
public class User {

    private String name;
    private int x;
    private int y;
    private int radius;
    private Mode mode;
    private Map<String, Preference> preferences;

    public User(String name, int x, int y, int radius, Mode mode) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }
    
    public Map<String, Preference> getPreferences() {
    	if (preferences == null) preferences = new TreeMap<String, Preference>();
        return preferences;
    }
    
    public Preference getPreferenceByName (String name) {
    	return getPreferences().get(name);
    }
    
    public Preference getPreferenceByPosition (int position) {
    	Collection<Preference> preferences = getPreferences().values();
        if (preferences == null) return null;
        if (position >= preferences.size()) return null;
    	Iterator<Preference> j = preferences.iterator();
    	Preference p = null;
    	for (int i=0;i<=position;++i) {
    		if (j.hasNext()) {
    			p = j.next();
    		} else {
    			p = null;
    		}
    	}
    	return p;
    }

	public void addPreference(Preference p) {
		getPreferences().put(p.getName(), p);
	}
}
