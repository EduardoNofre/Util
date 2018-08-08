package edu.coreUtil.genericsJDBC;

/**
 * 
 * 
 * @author Reginaldo Costa
 *
 */

public enum TipoDriver {

	ORACLE("oracle.jdbc.driver.OracleDriver"),
	SQLSERVER("com.microsoft.sqlserver.jdbc.SQLServerDriver"), 
	MYSQL("com.mysql.jdbc.Driver"), 
	H2("org.h2.Driver"), 
	JTDS("net.sourceforge.jtds.jdbc.Driver");
	
	private String	value;

	private TipoDriver(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
