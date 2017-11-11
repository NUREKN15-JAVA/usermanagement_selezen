package ua.nure.selezen.anastasiia.kn156.db;

import java.sql.Connection;

public interface ConnectionFactory {

	Connection createConnection() throws DatabaseExeption;
	
}
