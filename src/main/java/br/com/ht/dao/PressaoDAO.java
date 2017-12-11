package br.com.ht.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.ht.entidades.ht_pressao;
import br.com.ht.util.JPAUtil;

public class PressaoDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Query query;
	private EntityManager manager;
	private LogGeralDAO log;

	public PressaoDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void salvar(ht_pressao ht) {
		if (ht.getId() == 0) {
			this.log = new LogGeralDAO();
			this.manager.persist(ht);
			this.log.criarLog("Usuário: " + ht.getIdCadastro(), "ht_pressao", true, false, false, "Data: "
					+ ht.getDataInclusao() + ", Pressão: " + ht.getPrimeiroValor() + "/" + ht.getSegundoValor());
		}
	}

	public void alterar(ht_pressao ht) {
		this.log = new LogGeralDAO();
		this.manager.merge(ht);
		this.log.criarLog("Usuário: " + ht.getIdCadastro(), "ht_pressao", false, true, false,
				"Data: " + ht.getDataInclusao() + ", Pressão: " + ht.getPrimeiroValor() + "/" + ht.getSegundoValor());

	}

	public void excluir(ht_pressao ht) {
		this.log = new LogGeralDAO();
		Object c = this.manager.merge(ht);
		this.manager.remove(c);
		this.log.criarLog("Usuário: " + ht.getIdCadastro(), "ht_pressao", false, false, true,
				"Data: " + ht.getDataInclusao() + ", Pressão: " + ht.getPrimeiroValor() + "/" + ht.getSegundoValor());

	}

	@SuppressWarnings("unchecked")
	public List<ht_pressao> listarTodasPressoesDoUsuario(int idUsuario) {
		List<ht_pressao> listarTabela = new ArrayList<>();
		this.query = this.manager.createNativeQuery(
				"select id, primeiroValor, segundoValor, dataInclusao, idCadastro from healthTrack.dbo.ht_pressao where idCadastro = :p1 order by dataInclusao DESC ");
		this.query.setParameter("p1", idUsuario);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				ht_pressao ht = new ht_pressao();
				if (obj[0] != null) {
					ht.setId(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					ht.setPrimeiroValor(obj[1].toString());
				}
				if (obj[2] != null) {
					ht.setSegundoValor(obj[2].toString());
				}
				if (obj[3] != null) {
					ht.setDataInclusao((Date) obj[3]);
				}
				if (obj[4] != null) {
					ht.setIdCadastro(Integer.valueOf(obj[4].toString()));
				}
				listarTabela.add(ht);
			}
		}
		return listarTabela;
	}
	
	@SuppressWarnings("unchecked")
	public List<ht_pressao> listarDashboard(int idUsuario) {
		List<ht_pressao> listarTabela = new ArrayList<>();
		this.query = this.manager.createNativeQuery(
				"select top 10 id, primeiroValor, segundoValor, dataInclusao, idCadastro from healthTrack.dbo.ht_pressao where idCadastro = :p1 order by dataInclusao DESC ");
		this.query.setParameter("p1", idUsuario);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				ht_pressao ht = new ht_pressao();
				if (obj[0] != null) {
					ht.setId(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					ht.setPrimeiroValor(obj[1].toString());
				}
				if (obj[2] != null) {
					ht.setSegundoValor(obj[2].toString());
				}
				if (obj[3] != null) {
					ht.setDataInclusao((Date) obj[3]);
				}
				if (obj[4] != null) {
					ht.setIdCadastro(Integer.valueOf(obj[4].toString()));
				}
				listarTabela.add(ht);
			}
		}
		return listarTabela;
	}

	@SuppressWarnings("unchecked")
	public List<ht_pressao> listarPressaoEspecifica(int idUsuario, int id) {
		List<ht_pressao> listarTabela = new ArrayList<>();
		this.query = this.manager.createNativeQuery(
				"select id, primeiroValor, segundoValor, dataInclusao, idCadastro from healthTrack.dbo.ht_pressao where idCadastro = :p1 and id = :p2 order by dataInclusao DESC ");
		this.query.setParameter("p1", idUsuario);
		this.query.setParameter("p2", id);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				ht_pressao ht = new ht_pressao();
				if (obj[0] != null) {
					ht.setId(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					ht.setPrimeiroValor(obj[1].toString());
				}
				if (obj[2] != null) {
					ht.setSegundoValor(obj[2].toString());
				}
				if (obj[3] != null) {
					ht.setDataInclusao((Date) obj[3]);
				}
				if (obj[4] != null) {
					ht.setIdCadastro(Integer.valueOf(obj[4].toString()));
				}
				listarTabela.add(ht);
			}
		}
		return listarTabela;
	}

}
