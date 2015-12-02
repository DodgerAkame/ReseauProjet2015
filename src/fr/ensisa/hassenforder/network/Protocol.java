package fr.ensisa.hassenforder.network;

public interface Protocol {

	public static final Object EXIT_TEXT = "exit";

	public static final int PROXIMITY_PORT_ID = 1235;

        public final int  GET_LOGIN = 1;
	public final int REQ_PREF = 2;
	public final int REQ_MOV = 3;
	public final int REQ_RAD = 4;
	public final int GET_USER = 5;
	public final int REQ_PROPUPVIS = 6;
        public final int REP_OK = 7;
        public final int REP_KO = 8;
        public final int REP_LOGIN = 9;
        public final int REP_USER = 10;
        public final int REP_USERS = 11;
        public final int REQ_MODE = 12;
        public final int REQ_PROPUPLEV = 13;
        
        
}
