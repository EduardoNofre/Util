package edu.coreUtil.threads;

import java.util.Set;

/**
 * @author Eduardo Nofre
 *
 */
public class ThreadsUtil {

	/**
	 * 
	 * @listaThreadsAtiva: Verifica se a o nome da Thread passado como parametro
	 *                     esta ativa, se a Thread estiver ativa o metodo retorna
	 *                     true
	 * 
	 * @param nomeThread
	 *          : nome da Thread que deseja saber se está ativa
	 * 
	 * @return : boolean
	 * 
	 */

	protected boolean threadsAtiva(String nomeThread) {

		boolean threadRodando = false;

		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();

		Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);

		for (Thread thread : threadArray) {

			System.out.println(thread.getName());

			if (thread.getName().equals(nomeThread)) {

				threadRodando = true;

			}
		}
		return threadRodando;
	}

	/**
	 * 
	 * @criarThreads:
	 * 
	 * @criarThreads: Esse metodo cria threads com o nome passado como parametro
	 * 
	 * @param nomeThread
	 *          : Nome que deseja da a thread
	 * @param t
	 *          : tipo classe genrecia
	 */

	protected <T> void criarThreads(String nomeThread, T t) {

		Thread threadAzul = new Thread((Runnable) t);

		threadAzul.setName(nomeThread);

		threadAzul.start();

	}

	/**
	 * 
	 * @matarThreads: utilizado para interromper a thread
	 * 
	 * @param nomeThread
	 * @param t
	 */

	protected <T> boolean destroiThreads(String nomeThread, T t) {

		boolean threadMatar = false;

		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();

		Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);

		for (Thread thread : threadArray) {

			System.out.println(thread.getName());

			if (thread.getName().equals(nomeThread)) {

				thread.destroy();

				threadMatar = true;

			}
		}
		return threadMatar;
	}

}
