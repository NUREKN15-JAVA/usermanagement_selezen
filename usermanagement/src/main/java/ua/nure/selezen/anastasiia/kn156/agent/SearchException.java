package ua.nure.selezen.anastasiia.kn156.agent;

import ua.nure.selezen.anastasiia.kn156.db.DatabaseExeption;

public class SearchException extends Exception {

	public SearchException(DatabaseExeption e) {
		 super();
	}
    public SearchException() {
        super();
    }

    public SearchException(String message) {
        super(message);
    }

    public SearchException(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchException(Throwable cause) {
        super(cause);
    }
}
