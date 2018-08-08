package edu.coreUtil.genericsJDBC;

/**
 * 
 * 
 * @author Reginaldo Costa
 *
 */

public enum TipoDataSource {

	DERBY("org.apache.derby.jdbc.ClientDataSource"), 
	JAYBIRD("org.firebirdsql.pool.FBSimpleDataSource"), 
	H2("org.h2.jdbcx.JdbcDataSource"), 
	HSQLDB("org.hsqldb.jdbc.JDBCDataSource"), 
	IBMJCC("com.ibm.db2.jcc.DB2SimpleDataSource"), 
	IBMINFORMIX("com.informix.jdbcx.IfxDataSource"), 
	MICROSOFT("com.microsoft.sqlserver.jdbc.SQLServerDataSource"), 
	CONNECTORJ("com.mysql.jdbc.jdbc2.optional.MysqlDataSource"), 
	MARIADB("org.mariadb.jdbc.MySQLDataSource"), 
	ORACLE("oracle.jdbc.pool.OracleDataSource"), 
	ORIENTDB("com.orientechnologies.orient.jdbc.OrientDataSource"), 
	PGJDBCNG("com.impossibl.postgres.jdbc.PGDataSource"), 
	POSTGRESQL("org.postgresql.ds.PGSimpleDataSource"), 
	SAP("com.sap.dbtech.jdbc.DriverSapDB"), 
	XERIAL("org.sqlite.SQLiteDataSource"), 
	JCONNECT("com.sybase.jdbc4.jdbc.SybDataSource");

	private String	value;

	private TipoDataSource(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
