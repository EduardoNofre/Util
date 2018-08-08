package edu.coreUtil.schedule;

import java.util.Calendar;

/**
 * @author eduardo
 *
 */
public abstract class SchedulePropertiesUtil implements Runnable {

	private int			agendaAno;

	private int			agendaMes;

	private int			agendaDia;

	private int			agendaHora;

	private int			agendaMinuto;

	private boolean	agendado;

	private boolean	executando;

	private long		intervaloExecucao	= 3600000;

	public void run() {

		while (true) {

			this.executando = true;

			int horaAgora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

			int minutoAgora = Calendar.getInstance().get(Calendar.MINUTE);

			int diaAgora = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

			int mesAgora = Calendar.getInstance().get(Calendar.MONTH);

			int anoAgora = Calendar.getInstance().get(Calendar.YEAR);

			if (isLigado()) {

				if (agendado) {
					if (anoAgora == agendaAno && mesAgora == agendaMes && diaAgora == agendaDia && horaAgora == agendaHora && minutoAgora == agendaMinuto) {
						this.tarefa();
					}
				} else {
					this.tarefa();
				}

			}

			try {
				Thread.sleep(this.intervaloExecucao);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}

			this.executando = false;

		}
	}

	public boolean isExecutando() {
		return this.executando;
	}

	/**
	 * 
	 * Set o intervalo de execução do schedule *
	 * 
	 * @param intervalo
	 * 
	 */

	public void setIntervaloExecucao(long intervalo) {

		if (this.agendado) {

			this.intervaloExecucao = intervaloExecucao;

		} else {

			this.intervaloExecucao = intervalo;
		}
	}

	/**
	 * Metodo: agenda o dia e hora da execução
	 * 
	 * @param tempo
	 * 
	 */

	public void agendarTarefa(Calendar tempo) {

		if (tempo != null) {

			this.agendaAno = tempo.get(Calendar.YEAR);

			this.agendaMes = tempo.get(Calendar.MONTH);

			this.agendaDia = tempo.get(Calendar.DAY_OF_MONTH);

			this.agendaHora = tempo.get(Calendar.HOUR_OF_DAY);

			this.agendaMinuto = tempo.get(Calendar.MINUTE);

			this.agendado = true;

			this.intervaloExecucao = intervaloExecucao;

		} else {

			this.agendado = false;
		}
	}

	public abstract void tarefa();

	public abstract boolean isLigado();
}
