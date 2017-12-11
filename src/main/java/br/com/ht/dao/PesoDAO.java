package br.com.ht.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.ht.entidades.ht_peso;
import br.com.ht.util.JPAUtil;

public class PesoDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Query query;
	private EntityManager manager;
	private LogGeralDAO log;

	public PesoDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void salvar(ht_peso ht) {
		if (ht.getId() == 0) {
			this.log = new LogGeralDAO();
			this.manager.persist(ht);
			this.log.criarLog("Usuário: " + ht.getIdCadastro(), "ht_peso", true, false, false,
					"Data: " + ht.getDataAlteracao() + ", Peso: " + ht.getPeso());
		}
	}

	public void alterar(ht_peso ht) {
		this.log = new LogGeralDAO();
		this.manager.merge(ht);
		this.log.criarLog("Usuário: " + ht.getIdCadastro(), "ht_peso", false, true, false,
				"Data: " + ht.getDataAlteracao() + ", Peso: " + ht.getPeso());
	}

	public void excluir(ht_peso ht) {
		this.log = new LogGeralDAO();
		Object c = this.manager.merge(ht);
		this.manager.remove(c);
		this.log.criarLog("Usuário: " + ht.getIdCadastro(), "ht_peso", false, false, true,
				"Data: " + ht.getDataAlteracao() + ", Peso: " + ht.getPeso());
	}

	@SuppressWarnings("unchecked")
	public List<ht_peso> listarTodosPesosDoUsuario(int idUsuario) {
		List<ht_peso> listarTabela = new ArrayList<>();
		this.query = this.manager.createNativeQuery(
				"select id, peso, dataAlteracao, idCadastro from healthTrack.dbo.ht_peso where idCadastro = :p1 order by dataAlteracao DESC ");
		this.query.setParameter("p1", idUsuario);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				ht_peso ht = new ht_peso();
				if (obj[0] != null) {
					ht.setId(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					ht.setPeso(Double.valueOf(obj[1].toString()));
				}
				if (obj[2] != null) {
					ht.setDataAlteracao((Date) obj[2]);
				}
				if (obj[3] != null) {
					ht.setIdCadastro(Integer.valueOf(obj[3].toString()));
				}
				listarTabela.add(ht);
			}
		}
		return listarTabela;
	}

	@SuppressWarnings("unchecked")
	public List<ht_peso> listarDashboard(int idUsuario) {
		List<ht_peso> listarTabela = new ArrayList<>();
		this.query = this.manager.createNativeQuery(
				"select top 10 id, peso, dataAlteracao, idCadastro from healthTrack.dbo.ht_peso where idCadastro = :p1 order by dataAlteracao DESC ");
		this.query.setParameter("p1", idUsuario);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				ht_peso ht = new ht_peso();
				if (obj[0] != null) {
					ht.setId(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					ht.setPeso(Double.valueOf(obj[1].toString()));
				}
				if (obj[2] != null) {
					ht.setDataAlteracao((Date) obj[2]);
				}
				if (obj[3] != null) {
					ht.setIdCadastro(Integer.valueOf(obj[3].toString()));
				}
				listarTabela.add(ht);
			}
		}
		return listarTabela;
	}

	@SuppressWarnings("unchecked")
	public List<ht_peso> listarPesosEspecifico(int idUsuario, int id) {
		List<ht_peso> listarTabela = new ArrayList<>();
		this.query = this.manager.createNativeQuery(
				"select id, peso, dataAlteracao, idCadastro from healthTrack.dbo.ht_peso where id = :p1 and idCadastro = :p2 order by dataAlteracao DESC ");
		this.query.setParameter("p1", idUsuario);
		this.query.setParameter("p2", id);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				ht_peso ht = new ht_peso();
				if (obj[0] != null) {
					ht.setId(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					ht.setPeso(Double.valueOf(obj[1].toString()));
				}
				if (obj[2] != null) {
					ht.setDataAlteracao((Date) obj[2]);
				}
				if (obj[3] != null) {
					ht.setIdCadastro(Integer.valueOf(obj[3].toString()));
				}
				listarTabela.add(ht);
			}
		}
		return listarTabela;
	}

}
