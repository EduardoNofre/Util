package edu.coreUtil.genericDao;

/**
 * 
 * @author Eduardo Nofre
 *  
 */

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceFactory {

	private static EntityManagerFactory	emf;

	private static EntityManager				em;

	// by Eduardo Nofre
	public static EntityManager InformationUnit(String unitXML) {

		if (emf == null) {

			emf = Persistence.createEntityManagerFactory(unitXML);

		}

		return PersistenceFactory.createEntityManager();

	}

	public static EntityManager createEntityManager() {

		if (em == null || !em.isOpen()) {

			return em = emf.createEntityManager();

		} else {

			return em;
		}
	}

	public static boolean closeEntityManager() {

		try {

			em.close();

			return true;

		} catch (Exception e) {

			return false;
		}
	}

}
