package edu.coreUtil.date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.chrono.ISOChronology;

import de.jollyday.Holiday;
import de.jollyday.HolidayManager;
import net.objectlab.kit.datecalc.common.DateCalculator;
import net.objectlab.kit.datecalc.common.DefaultHolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayHandlerType;
import net.objectlab.kit.datecalc.joda.LocalDateKitCalculatorsFactory;

public class DataUtil {

	/**
	 * soma os dias em uma para passada como parametro
	 * 
	 * @param data
	 * @param dias
	 * @return
	 * 
	 */

	protected long somaDiasEmUmaData(long data,int dias){

		Calendar calendar = Calendar.getInstance();

		calendar.setTimeInMillis(data);

		calendar.add(Calendar.DAY_OF_MONTH, +dias);

		return calendar.getTimeInMillis();

	}

	/**
	 * subtrai os dias em uma para passada como parametro
	 * 
	 * @param data
	 * @param dias
	 * @return
	 * 
	 */

	protected long subtraiDiasEmUmaData(long data,int dias){

		Calendar calendar = Calendar.getInstance();

		calendar.setTimeInMillis(data);

		calendar.add(Calendar.DAY_OF_MONTH, -dias);

		return calendar.getTimeInMillis();

	}

	/**
	 * soma os mes em uma para passada como parametro
	 * 
	 * @param data
	 * @param mes
	 * @return
	 * 
	 */

	protected long somaMesEmUmaData(long data,int mes){

		Calendar calendar = Calendar.getInstance();

		calendar.setTimeInMillis(data);

		calendar.add(Calendar.MONTH, +mes);

		return calendar.getTimeInMillis();

	}

	/**
	 * subtrai os mes em uma para passada como parametro
	 * 
	 * @param data
	 * @param dias
	 * @return
	 * 
	 */

	protected long subtraiMesEmUmaData(long data,int mes){

		Calendar calendar = Calendar.getInstance();

		calendar.setTimeInMillis(data);

		calendar.add(Calendar.MONTH, -mes);

		return calendar.getTimeInMillis();

	}

	/**
	 * 
	 * @param dataDetalhadaInicio
	 * @param dataDetalhadaFim
	 * 
	 */

	protected java.util.List<LocalDate> diasUteisData(DataDetalhada dataDetalhadaInicio,DataDetalhada dataDetalhadaFim){

		java.util.List<LocalDate> dataDiautil = new ArrayList<LocalDate>();

		DateTime datainicio = new DateTime(dataDetalhadaInicio.getAno(), dataDetalhadaInicio.getMes(),
				dataDetalhadaInicio.getDias(), dataDetalhadaInicio.getHora(), dataDetalhadaInicio.getMinuto());

		DateTime dataFim = new DateTime(dataDetalhadaFim.getAno(), dataDetalhadaFim.getMes(),
				dataDetalhadaFim.getDias(), dataDetalhadaFim.getHora(), dataDetalhadaFim.getMinuto());

		HolidayManager gerenciadorDeFeriados = HolidayManager.getInstance(de.jollyday.HolidayCalendar.BRAZIL);

		Set<Holiday> feriados = gerenciadorDeFeriados.getHolidays(new DateTime().getYear());

		Set<LocalDate> dataDosFeriados = new HashSet<LocalDate>();

		for (Holiday h : feriados) {

			dataDosFeriados.add(new LocalDate(h.getDate(), ISOChronology.getInstance()));
		}

		HolidayCalendar<LocalDate> calendarioDeFeriados = new DefaultHolidayCalendar<LocalDate>(dataDosFeriados);

		LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays("BR", calendarioDeFeriados);

		DateCalculator<LocalDate> calendario = LocalDateKitCalculatorsFactory.getDefaultInstance()
				.getDateCalculator("BR", HolidayHandlerType.FORWARD);

		calendario.isNonWorkingDay(new LocalDate(datainicio));

		calendario.isNonWorkingDay(new LocalDate(dataFim));

		LocalDate datainicioTemporaria = new LocalDate(datainicio);

		LocalDate dataFimTemporaria = new LocalDate(dataFim);

		while (!datainicioTemporaria.isAfter(dataFimTemporaria)) {

			if (!calendario.isNonWorkingDay(datainicioTemporaria)) {

				dataDiautil.add(datainicioTemporaria);
			}
			datainicioTemporaria = datainicioTemporaria.plusDays(1);
		}
		return dataDiautil;
	}

	/**
	 * 
	 * @param dataDetalhadaInicio
	 * @param dataDetalhadaFim
	 * 
	 */

	protected java.util.List<LocalDate> diasNaoUteisData(DataDetalhada dataDetalhadaInicio,
			DataDetalhada dataDetalhadaFim){

		java.util.List<LocalDate> dataNaoutil = new ArrayList<LocalDate>();

		DateTime datainicio = new DateTime(dataDetalhadaInicio.getAno(), dataDetalhadaInicio.getMes(),
				dataDetalhadaInicio.getDias(), dataDetalhadaInicio.getHora(), dataDetalhadaInicio.getMinuto());

		DateTime dataFim = new DateTime(dataDetalhadaFim.getAno(), dataDetalhadaFim.getMes(),
				dataDetalhadaFim.getDias(), dataDetalhadaFim.getHora(), dataDetalhadaFim.getMinuto());

		HolidayManager gerenciadorDeFeriados = HolidayManager.getInstance(de.jollyday.HolidayCalendar.BRAZIL);

		Set<Holiday> feriados = gerenciadorDeFeriados.getHolidays(new DateTime().getYear());

		Set<LocalDate> dataDosFeriados = new HashSet<LocalDate>();

		for (Holiday h : feriados) {

			dataDosFeriados.add(new LocalDate(h.getDate(), ISOChronology.getInstance()));
		}

		HolidayCalendar<LocalDate> calendarioDeFeriados = new DefaultHolidayCalendar<LocalDate>(dataDosFeriados);

		LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays("BR", calendarioDeFeriados);

		DateCalculator<LocalDate> calendario = LocalDateKitCalculatorsFactory.getDefaultInstance()
				.getDateCalculator("BR", HolidayHandlerType.FORWARD);

		calendario.isNonWorkingDay(new LocalDate(datainicio));

		calendario.isNonWorkingDay(new LocalDate(dataFim));

		LocalDate datainicioTemporaria = new LocalDate(datainicio);

		LocalDate dataFimTemporaria = new LocalDate(dataFim);

		while (!datainicioTemporaria.isAfter(dataFimTemporaria)) {

			if (calendario.isNonWorkingDay(datainicioTemporaria)) {

				dataNaoutil.add(datainicioTemporaria);

			}
			datainicioTemporaria = datainicioTemporaria.plusDays(1);
		}
		return dataNaoutil;
	}

	/**
	 * 
	 * @param ano
	 * 
	 */

	protected java.util.List<Holiday> diasFeriadosData(int ano){

		java.util.List<Holiday> dataNaoutil = new ArrayList<Holiday>();

		HolidayManager gerenciadorDeFeriados = HolidayManager.getInstance(de.jollyday.HolidayCalendar.BRAZIL);

		Set<Holiday> feriados = gerenciadorDeFeriados.getHolidays(ano);

		Set<LocalDate> dataDosFeriados = new HashSet<LocalDate>();

		for (Holiday h : feriados) {

			dataDosFeriados.add(new LocalDate(h.getDate(), ISOChronology.getInstance()));

			dataNaoutil.add(h);
		}
		return dataNaoutil;

	}

	/**
	 * 
	 * @param data
	 */

	protected boolean dataMaiorOuMenor(Date dataInicio,Date dataFim){

		return dataInicio.after(dataFim);

	}

	/**
	 * 
	 * @param data
	 * @param maskData
	 */

	protected String formataData(Date data,TipoMaskDate maskData){

		SimpleDateFormat sdf = new SimpleDateFormat(maskData.getMaskData());

		return sdf.format(data);

	}

	/**
	 * @calculaDiferecaEntreDatas
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return
	 */

	protected DataDetalhada calculaDiferecaEntreDatasDetalhado(long dataInicio,long dataFim){

		DataDetalhada dataDetalhada = new DataDetalhada();

		DateTime inicio = new DateTime(dataInicio);

		DateTime fim = new DateTime(dataFim);

		Duration diferencaData = new Duration(inicio, fim);

		dataDetalhada.setDias((int) diferencaData.getStandardDays());

		dataDetalhada.setHora((int) diferencaData.getStandardHours());

		dataDetalhada.setMinuto((int) diferencaData.getStandardMinutes());

		dataDetalhada.setSegundos((int) diferencaData.getStandardSeconds());

		return dataDetalhada;
	}

}
