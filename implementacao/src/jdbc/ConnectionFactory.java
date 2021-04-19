package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ANDRADE
 */
public class ConnectionFactory {
	// Cria a conexão
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1/project_pulse", "root", "1234567");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
