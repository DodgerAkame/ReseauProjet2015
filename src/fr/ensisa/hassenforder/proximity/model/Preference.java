package fr.ensisa.hassenforder.proximity.model;

/**
 *
 * @author hassenforder
 */
public class Preference {
    private String name;
    private int level;
    private boolean visibility;

    public Preference() {
    }

    public Preference(String name, int level, boolean visibility) {
        this.name = name;
        this.level = level;
        this.visibility = visibility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
    
    
}
