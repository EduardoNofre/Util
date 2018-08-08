package edu.coreUtil.genericsJDBC;

/**
 * 
 * 
 * @author Reginaldo Costa
 *
 */

public class PrincipalJDBC extends JDBCAbstractConnection {

	@Override
	public String getUrl(){
		return "jdbc:mysql://localhost:3306/criptografiajavateste";
	}

	@Override
	public String getUser(){
		return "root";
	}

	@Override
	public String getPassword(){
		return "root";
	}

	@Override
	public TipoDriver getTipoDriver(){
		return TipoDriver.MYSQL;
	}
}
