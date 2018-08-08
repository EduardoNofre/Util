package edu.coreUtil.threads;

public class ServicoThreads {

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

	public static boolean threadsAtiva(String nomeThread) {

		ThreadsUtil thread = new ThreadsUtil();

		return thread.threadsAtiva(nomeThread);

	}

	/**
	 * 
	 * @criarThreads: criarThreads
	 * 
	 * @criarThreads: Esse metodo cria threads com o nome passado como parametro
	 * 
	 * @param nomeThread
	 *          : Nome que deseja da a thread
	 * @param t
	 *          : tipo classe genrecia
	 */

	public static <T> void criarThreads(String nomeThread, T t) {

		ThreadsUtil thread = new ThreadsUtil();

		thread.criarThreads(nomeThread, t);

	}

	/**
	 * 
	 * @matarThreads: utilizado para interromper, matar a thread
	 * 
	 * @param nomeThread
	 *          : Nome da thread que deseja matar
	 * @param t
	 *          : tipo generico objeto
	 */

	public static <T> boolean destroiThreads(String nomeThread, T t) {

		ThreadsUtil thread = new ThreadsUtil();

		return thread.destroiThreads(nomeThread, t);

	}
}
