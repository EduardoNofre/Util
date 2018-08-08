package edu.coreUtil.genericsJDBC;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * 
 * @author Reginaldo Costa
 *
 */

public abstract class JDBCAbstractPooledConnection extends JDBCAbstract implements JDBCConnection {

	@Override
	public Connection getConnection(){

		try {
			if (conn != null && !conn.isClosed()) {
				return conn;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {

			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(this.getUrl());
			config.setUsername(this.getUser());
			config.setPassword(this.getPassword());
			config.setDataSourceClassName(this.getTipoDataSource().toString());
			config.addDataSourceProperty("cachePrepStmts", "true");
			config.addDataSourceProperty("prepStmtCacheSize", "250");
			config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

			HikariDataSource ds = new HikariDataSource(config);

			conn = ds.getConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;

	}

	@Override
	public TipoDriver getTipoDriver(){
		// This is not necessary here
		return null;
	}

}
