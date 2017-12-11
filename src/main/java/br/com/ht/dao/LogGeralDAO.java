package br.com.ht.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EntityManager;

import br.com.ht.entidades.log_geral;
import br.com.ht.util.JPAUtil;

public class LogGeralDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager manager;

	public LogGeralDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void criarLog(String feitoPor, String tabela, boolean inserir, boolean alterar, boolean deletar,
			String informacao) {
		log_geral lg = new log_geral();
		lg.setAlterar(alterar);
		lg.setDeletar(deletar);
		lg.setFeitoPor(feitoPor);
		lg.setInformacao(informacao);
		lg.setInserir(inserir);
		lg.setTabela(tabela);
		lg.setData(new Date());
		this.manager.persist(lg);
	}
}
