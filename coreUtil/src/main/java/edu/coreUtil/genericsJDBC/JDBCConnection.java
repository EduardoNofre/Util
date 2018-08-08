package edu.coreUtil.genericsJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

/**
 * 
 * 
 * @author Reginaldo Costa
 *
 */

public interface JDBCConnection {

	Connection getConnection();

	<T> List<T> findList(String query,Class<T> genercsClass,Object... parameters);

	<T> T findObject(String query,Class<T> genercsClass,Object... parameters);

	List<Object[]> findObjectArrayList(String query,Object... parameters);

	Object[] findObjectArray(String query,Object... parameters);

	ResultSet findResultSet(String query);

	String getUrl();

	String getUser();

	String getPassword();

	TipoDriver getTipoDriver();

	TipoDataSource getTipoDataSource();

	void closeConnection(Connection conn);

	Long insert(String query,Object... parameters);

	int update(String query,Object[] parameters);

	int delete(String query,Object[] parameters);

}
