package eu.europa.ec.etrustex.tools.translation.db;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Accesses the DB.
 *
 * @author keschma
 *
 */
public class DbAccessor {
		
	private final static Logger LOGGER = Logger.getLogger(DbAccessor.class);
		
	private String user;
	
	private String password;
	
	private String driverClass;
	
	private String jdbcUrl;
	
	private Connection connection;
	
	public DbAccessor(String user, String password, String driverClass, String jdbcUrl) {
		this.user = user;
		this.password = password;
		this.driverClass = driverClass;
		this.jdbcUrl = jdbcUrl;
		
		LOGGER.info("DB user    : " + user);
		LOGGER.info("DB password: " + password);
		LOGGER.info("DB driver  : " + driverClass);
		LOGGER.info("DB url     : " + jdbcUrl);		
	}
	
	/**
	 * Opens the DbAccessor' connection to the database.
	 */
	public void open() {
		LOGGER.info("Initialising driver: " + driverClass);
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			LOGGER.error("Unable to load driver class: " + driverClass);
			throw new IllegalStateException(e);
		}
		
		LOGGER.info("Acquiring connection: " + jdbcUrl + " " + user + "/" + password);
		try {
			connection = DriverManager.getConnection(jdbcUrl, user, password);
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			LOGGER.error("Unable to acquire connection with: " + jdbcUrl + " " + user + "/" + password);
			throw new IllegalStateException(e);
		}		
	}	
	
	/**
	 * Returns the current DB connection.
	 * @return
	 */
	public Connection getConnection() {
		return connection;
	}
	
	/**
	 * Closes the DbAccessor's connection to the database.
	 * @param doCommit whether the current transaction should be committed
	 * (true) or rolled back (false)
	 */
	public void close(boolean doCommit) {
		try (Connection con = connection){
			if (!con.isClosed()) {
				if (doCommit) {
					LOGGER.info("Committing transaction.");
					con.commit();
				} else {
					LOGGER.info("Rolling back transaction.");
					con.rollback();
				}
			}
		} catch (SQLException e) {
			LOGGER.warn("Could not close JDBC connection. Continuing.");
		}
	}

}
