package com.tcibpo.content.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.tcibpo.content.exception.BusinessException;

public class SchedulerProperties {

	private Properties prop = new Properties();
	
	public SchedulerProperties(String folderConfiguration) throws BusinessException {
		try {
			String file = folderConfiguration + File.separatorChar + "scheduler.properties";
			SchedulerProperties.validaArquivo(file);
			prop.load(new FileInputStream(file));
		} catch (IOException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	public static void validaArquivo(String arquivo) throws FileNotFoundException {
		if(new File(arquivo).exists() == false){
			throw new FileNotFoundException("arquivo nao encontrado no caminho: " + arquivo);
		}
	}
	
	public boolean jobArvoreDocumentosEstaAtivo() {
		boolean property = Boolean.parseBoolean(prop.getProperty("jobs.MontarEstrutaPastasJob.ativo"));
		return property;
	}

	public int getJobArvoreDocumentosTempoCiclo() {
		int property = new Integer(prop.getProperty("jobs.MontarEstrutaPastasJob.tempoCiclo"));
		return property;
	}

	public int getJobArvoreDocumentosQuantidadeThreads() {
		int property = new Integer(prop.getProperty("jobs.MontarEstrutaPastasJob.qtdThread"));
		return property;
	}
	
	public boolean jobTratamentoVRSEstaAtivo() {
		boolean property = Boolean.parseBoolean(prop.getProperty("jobs.tratamentoImagemVRS.ativo"));
		return property;
	}
	
	public int getJobTratamentoVRSTempoCiclo() {
		int property = new Integer(prop.getProperty("jobs.tratamentoImagemVRS.tempoCiclo"));
		return property;
	}
	

	public int getQuantidadeThreadsTratamentoVRS() {
		int property = new Integer(prop.getProperty("jobs.tratamentoImagemVRS.qtdThread"));
		return property;
	}

	public boolean jobOCREstaAtivo() {
		boolean property = Boolean.parseBoolean(prop.getProperty("jobs.processoOCR.ativo"));
		return property;
	}
	
	public boolean jobZonaOCREstaAtivo() {
		boolean property = Boolean.parseBoolean(prop.getProperty("jobs.processoZonaOCR.ativo"));
		return property;
	}
	
	public boolean jobReprocessamentoOCREstaAtivo() {
		boolean property = Boolean.parseBoolean(prop.getProperty("jobs.reprocessamentoOCR.ativo"));
		return property;
	}
	
	public int getJobOCRTempoCiclo() {
		int property = new Integer(prop.getProperty("jobs.processoOCR.tempoCiclo"));
		return property;
	}
	
	public int getJobOCRQuantidadeThreads() {
		int property = new Integer(prop.getProperty("jobs.processoOCR.qtdThread"));
		return property;
	}
	
	public boolean jobBuscaTextualEstaAtivo() {
		boolean property = Boolean.parseBoolean(prop.getProperty("jobs.BuscaTextual.ativo"));
		return property;
	}

	public boolean jobBuscaTextualExcluirArquivoTexto() {
		boolean property = Boolean.parseBoolean(prop.getProperty("jobs.BuscaTextual.excluirArquivoTexto"));
		return property;
	}

	public int getJobBuscaTextualTempoCiclo() {
		int property = new Integer(prop.getProperty("jobs.BuscaTextual.tempoCiclo"));
		return property;
	}
	
	// propriedades blockchain

	public int getblockchainAtivo() {
		int property = new Integer(prop.getProperty("blockchain.ativo"));
		return property;
	}

	public int getblockchainAutomatico() {
		int property = new Integer(prop.getProperty("blockchain.manual.automatico"));
		return property;
	}

	public String getBlockchainUsuario() {
		String property = prop.getProperty("blockchain.usuario");
		return property;
	}

	public String getBlockchainSenha() {
		String property = prop.getProperty("blockchain.senha");
		return property;
	}

	public String getBlockchainDestino() {
		String property = prop.getProperty("blockchain.destino");
		return property;
	}
	
	public String getBlockchainStarLabsToken() {
		String property = prop.getProperty("blockchain.starLabs.token");
		return property;
	}
	
	public String getBlockchainStarLabsAccount() {
		String property = prop.getProperty("blockchain.starLabs.account");
		return property;
	}
	
	
	public String getBlockchainStarLabsUser() {
		String property = prop.getProperty("blockchain.starLabs.user");
		return property;
	}
	
	
	public String getBlockchainStarLabsSenha() {
		String property = prop.getProperty("blockchain.starLabs.senha");
		return property;
	}
	
	
	public String getBlockchainStarLabsTeste() {
		String property = prop.getProperty("blockchain.starLabs.teste");
		return property;
	}
	
	
	public String getBlockchainStarLabsProducao() {
		String property = prop.getProperty("blockchain.starLabs.producao");
		return property;
	}
}
