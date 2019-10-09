package edu.coreUtil.genericsJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * 
 *
 * 
 * @Observação: Essa Classe deve ser extendida toda vez que for utilizar alguma
 *              conexão com banco
 * 
 *
 */

public abstract class JDBCAbstractConnection extends JDBCAbstract implements JDBCConnection {

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

			Class.forName(this.getTipoDriver().getValue());
			conn = DriverManager.getConnection(this.url, this.user, this.password);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

	@Override
	public TipoDataSource getTipoDataSource(){
		return null;
	}

	public void closeConnection(Connection conn){
		try {

			if (conn != null) {

				conn.close();
			}

		} catch (SQLException ex) {

			System.out.println("Não foi possível fechar a conexão!");
		}
	}

}
