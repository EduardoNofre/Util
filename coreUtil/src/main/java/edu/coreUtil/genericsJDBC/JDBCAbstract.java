package edu.coreUtil.genericsJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 * 
 * 
 * @author Reginaldo Costa
 *
 */

abstract class JDBCAbstract implements JDBCConnection {

	Connection		conn;
	String			url				= null;
	TipoDriver		driver			= null;
	TipoDataSource	tipoDataSource	= null;
	String			user			= null;
	String			password		= null;

	public JDBCAbstract() {
		this.url = this.getUrl();
		this.driver = this.getTipoDriver();
		this.tipoDataSource = this.getTipoDataSource();
		this.user = this.getUser();
		this.password = this.getPassword();
	}

	@Override
	public List<Object[]> findObjectArrayList(String query,Object... parameters){

		ResultSetHandler<List<Object[]>> handler = new ResultSetHandler<List<Object[]>>() {

			public List<Object[]> handle(ResultSet rs) throws SQLException{

				if (!rs.next()) {

					return null;
				}

				List<Object[]> objectArrayList = new ArrayList<>();

				int cols = rs.getMetaData().getColumnCount();

				while (rs.next()) {

					Object[] objectArray = new Object[cols];

					for (int i = 0; i < cols; i++) {
						objectArray[i] = rs.getObject(i + 1);
					}

					objectArrayList.add(objectArray);

				}

				return objectArrayList;

			}

		};

		QueryRunner run = new QueryRunner();

		List<Object[]> list = null;
		try {

			list = run.query(this.getConnection(), query, handler, parameters);

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			this.closeConnection(this.getConnection());
		}

		return list;

	}

	@Override
	public Object[] findObjectArray(String query,Object... parameters){

		ResultSetHandler<Object[]> handler = new ResultSetHandler<Object[]>() {

			public Object[] handle(ResultSet rs) throws SQLException{

				if (!rs.next()) {

					return null;
				}

				ResultSetMetaData meta = rs.getMetaData();

				int cols = meta.getColumnCount();

				Object[] result = new Object[cols];

				for (int i = 0; i < cols; i++) {
					result[i] = rs.getObject(i + 1);
				}

				return result;

			}

		};

		QueryRunner run = new QueryRunner();

		Object[] objectArray = null;
		try {
			objectArray = run.query(this.getConnection(), query, handler, parameters);

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			this.closeConnection(this.getConnection());
		}

		return objectArray;

	}

	@Override
	public <T> T findObject(String query,Class<T> aClass,Object... parameters){

		QueryRunner run = new QueryRunner();

		ResultSetHandler<T> handler = new BeanHandler<T>(aClass);

		T object = null;

		try {

			object = run.query(this.getConnection(), query, handler, parameters);

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			this.closeConnection(this.getConnection());
		}

		return object;
	}

	/**
	 * 
	 * @findList: Faz o select na base de dados
	 * 
	 * @param query
	 *            : Esse parametro recebe um select com seus parametros exemplo:
	 *            "SELECT
	 *            c.idconfiguracaoArquivo,c.sequenciaArquivo,c.descricao,c.percorrer
	 *            FROM cfglayoutarqpdl c where 1 = ?"
	 * 
	 * @param genercsClass
	 *            :O parametro genercsClass é generica ela recebe qualquer
	 *            classe é a passada é a mesma que será retornada
	 * 
	 * 
	 * @param parameters
	 *            : O parameters recebe os valores do parameters são os valores
	 *            utilizado no seu where
	 * 
	 */

	@Override
	public <T> List<T> findList(String query,Class<T> genercsClass,Object... parameters){

		QueryRunner run = new QueryRunner();

		ResultSetHandler<List<T>> handler = new BeanListHandler<T>(genercsClass);

		List<T> list = null;

		try {

			list = run.query(this.getConnection(), query, handler, parameters);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;

	}

	/**
	 * 
	 * 
	 * @findResultSet: Este metodo por sua vez faz um select
	 * @Obs: como este metodo retorna um ResultSet o mesmo não pode ser fechado
	 *       com Close(), caso seja fechado o mesmo rettorna uma Exception de
	 *       informando que o ResultSet foi fechado e não pode recuperar os
	 *       dados
	 * 
	 */
	@Override
	public ResultSet findResultSet(String query){

		try {

			PreparedStatement prepareStatement = this.getConnection().prepareStatement(query);

			return prepareStatement.executeQuery();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return null;
	}

	/**
	 * 
	 * 
	 * @insert: faz a persistencia dos dados
	 * 
	 * @param query
	 *            : recebe a query de INSERT INTO table_name (
	 *            field1,field2,...fieldN ) VALUES ( value1, value2,...valueN )
	 * 
	 * @param parameters
	 *            :parametros varias de delete
	 * 
	 */

	@Override
	public Long insert(String query,Object... parameters){

		QueryRunner queryRunner = new QueryRunner();

		Long id = null;

		try {

			if (parameters != null) {

				id = queryRunner.insert(this.getConnection(), query, new ScalarHandler<Long>(), parameters);

			}
			else {

				id = queryRunner.insert(this.getConnection(), query, new ScalarHandler<Long>());

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return id;
	}

	/**
	 * 
	 * 
	 * @delete: remove os registros da base de dados
	 * 
	 * @param query
	 *            : recebe a query de delete from orders where id_users = 1 and
	 *            id_product = ?
	 * 
	 * @param parameters
	 *            :parametros de acordo com seu delete
	 * 
	 */

	@Override
	public int delete(String query,Object[] parameters){

		return update(query, parameters);

	}

	/**
	 * 
	 * 
	 * @update: remove os registros da base de dados
	 * 
	 * @param query
	 *            : recebe uma query de UPDATE table_name SET column1=value,
	 *            column2=value2,...WHERE some_column = some_value
	 * 
	 * @param parameters
	 *            :parametros de acordo com seu delete
	 * 
	 */

	@Override
	public int update(String query,Object[] parameters){

		QueryRunner queryRunner = new QueryRunner();

		int inserts = 0;

		try {

			if (parameters != null) {

				inserts = queryRunner.update(query, parameters);

			}
			else {

				inserts = queryRunner.update(query);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return inserts;
	}

}
