package fr.ensisa.hassenforder.proximity.server;

import fr.ensisa.hassenforder.proximity.model.Mode;
import fr.ensisa.hassenforder.proximity.model.Preference;
import fr.ensisa.hassenforder.proximity.model.User;

public class UserFactory {

	static public User createHassen () {
		User user = new User ("hassen", 50, 50, 30, Mode.VISIBLE);
		user.addPreference (new Preference("football", -5, true));
		user.addPreference (new Preference("volleyball", 5, true));
		user.addPreference (new Preference("boire", 5, false));
		user.addPreference (new Preference("politique", 0, true));
		return user;
	}

	static public User createPamela () {
		User user = new User ("pamela", 20, 20, 30, Mode.VISIBLE);
		Preference preference;
		user.addPreference (new Preference("football", -8, true));
		user.addPreference (new Preference("boire", -6, false));
		return user;
	}

	static public User createSheila () {
		User user = new User ("sheila", 80, 80, 30, Mode.VISIBLE);
		Preference preference;
		user.addPreference (new Preference("manger", -4, false));
		user.addPreference (new Preference("volleyball", 5, true));
		return user;
	}

	static public User createGeorges () {
		User user = new User ("georges", 70, 40, 30, Mode.VISIBLE);
		Preference preference;
		user.addPreference (new Preference("boire", 6, false));
		user.addPreference (new Preference("manger", 6, false));
		return user;
	}

	static public User createAlfred () {
		User user = new User ("alfred", 10, 20, 30, Mode.VISIBLE);
		user.addPreference (new Preference("boire", 2, false));
		user.addPreference (new Preference("politique", 5, true));
		return user;
	}
}
