/**
 * 
 */
package edu.coreUtil.genericDao;

import java.util.List;

/**
 * @author eduardo
 *
 */
public interface GenericDao<T> {

	public boolean grava(T t);

	public boolean atualiza(T t);

	public boolean apagaRegistro(T t);

	public T buscaPeloId(int pk);

	public T buscaPeloIdString(String pk);
	
	public List<T> listaTodos(Class<T> classe);
}