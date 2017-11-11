package ua.nure.selezen.anastasiia.kn156.db;

import java.sql.SQLException;

public class DatabaseExeption extends Exception {

	public DatabaseExeption(Exception e) {
		super(e);
	}

	public DatabaseExeption(String string) {
		super(string);
	}

}