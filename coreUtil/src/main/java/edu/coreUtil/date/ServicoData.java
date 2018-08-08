package edu.coreUtil.date;

import java.text.ParseException;
import java.util.Date;

import org.joda.time.LocalDate;

import de.jollyday.Holiday;

/**
 * @author eduardo
 *
 */
public class ServicoData {

	/**
	 * @metodo: somaDiasEmUmaData
	 * 
	 *          Metodo que faz a soma de dias em uma data passada como parametro
	 *          e os dias que se deseja soma na data
	 * 
	 *          converter Date para long.
	 * 
	 *          Teste: ok
	 * 
	 * @param data
	 * @param dias
	 * @return
	 */

	public long somaDiasEmUmaData(long data,int dias){

		DataUtil dataUtil = new DataUtil();

		return dataUtil.somaDiasEmUmaData(data, dias);

	}

	/**
	 * @metodo: subtraiDiasEmUmaData
	 * 
	 *          Metodo que faz a subtração de dias em uma data passada como
	 *          parametro e os dias que se deseja subtrair da data
	 * 
	 *          converter Date para long.
	 * 
	 *          Teste: ok
	 * 
	 * @param data
	 * @param dias
	 * @return
	 */

	public long subtraiDiasEmUmaData(long data,int dias){

		DataUtil dataUtil = new DataUtil();

		return dataUtil.subtraiDiasEmUmaData(data, dias);

	}

	/**
	 * @metodo: somaMesEmUmaData
	 * 
	 *          Metodo que faz a soma de mes ou meses em uma data passada como
	 *          parametro e os mes que se deseja soma na data
	 * 
	 *          converter Date para long.
	 * 
	 *          Teste: ok
	 * 
	 * @param data
	 * @param mes
	 * @return
	 */
	
	public long somaMesEmUmaData(long data,int mes){

		DataUtil dataUtil = new DataUtil();

		return dataUtil.somaMesEmUmaData(data, mes);

	}

	/**
	 * 
	 * @metodo: subtraiMesEmUmaData
	 * 
	 *          subtrai os meses ou mes em uma data passada como parametro
	 * 
	 *          converter Date para long.
	 * 
	 *          Teste: ok
	 * 
	 * @param data
	 * @param dias
	 * @return
	 * 
	 */

	public long subtraiMesEmUmaData(long data,int mes){

		DataUtil dataUtil = new DataUtil();

		return dataUtil.subtraiMesEmUmaData(data, mes);

	}

	/**
	 * @Metodo: diasUteisData este metodo só retorna os dias uteis tirando
	 *          feriados e finais de semana
	 * 
	 *          setAno() setMes() setDias() setHora() setMinuto()
	 * 
	 *          Teste: ok
	 * 
	 * @param dataDetalhadaInicio
	 * @param dataDetalhadaFim
	 * @return
	 * 
	 */

	public java.util.List<LocalDate> diasUteisData(DataDetalhada dataDetalhadaInicio,DataDetalhada dataDetalhadaFim){

		DataUtil dataUtil = new DataUtil();

		return dataUtil.diasUteisData(dataDetalhadaInicio, dataDetalhadaFim);
	}

	/**
	 * @Metodo: diasNaoUteisData este metodo só retorna os dias que não são
	 *          uteis tirando feriados e finais de semana
	 * 
	 *          setAno() setMes() setDias() setHora() setMinuto()
	 * 
	 *          Teste: ok
	 * 
	 * @param dataDetalhadaInicio
	 * @param dataDetalhadaFim
	 * @return
	 * 
	 */
	
	public java.util.List<LocalDate> diasNaoUteisData(DataDetalhada dataDetalhadaInicio,DataDetalhada dataDetalhadaFim){

		DataUtil dataUtil = new DataUtil();

		return dataUtil.diasNaoUteisData(dataDetalhadaInicio, dataDetalhadaFim);
	}

	/**
	 * 
	 * @Metodo: Esse metodo retorna somente os feriados
	 * 
	 *          Teste: ok
	 * 
	 * @param ano
	 * @return
	 */

	public java.util.List<Holiday> diasFeriadosData(int ano){

		DataUtil dataUtil = new DataUtil();

		return dataUtil.diasFeriadosData(ano);
	}

	/**
	 * 
	 * @metodo: dataMaiorOuMenor verifica se a dataInicio é maior que a dataFim
	 * 
	 *          Teste: ok
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return
	 * 
	 */

	public boolean dataMaiorOuMenor(Date dataInicio,Date dataFim){

		DataUtil dataUtil = new DataUtil();

		return dataUtil.dataMaiorOuMenor(dataInicio, dataFim);
	}

	/**
	 * 
	 * @metodo: formataData utilizado para formatda a datas o mesmo tem um Enum
	 *          TipoMaskDate com os formatos permitido.
	 * 
	 *          Teste: ok
	 * 
	 * @param data
	 * @param maskData
	 * @return
	 * 
	 */
	
	public String formataData(Date data,TipoMaskDate maskData){

		DataUtil dataUtil = new DataUtil();

		return dataUtil.formataData(data, maskData);
	}
}
