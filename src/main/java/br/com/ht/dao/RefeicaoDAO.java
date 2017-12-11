package br.com.ht.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.ht.entidades.ht_refeicao;
import br.com.ht.entidades.ht_refeicao_tipo;
import br.com.ht.entidades.refeicao_join;
import br.com.ht.util.JPAUtil;

public class RefeicaoDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Query query;
	private EntityManager manager;
	private LogGeralDAO log;

	public RefeicaoDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void salvar(ht_refeicao ht) {
		if (ht.getId() == 0) {
			this.log = new LogGeralDAO();
			this.manager.persist(ht);
			this.log.criarLog("Usuário: " + ht.getIdCadastro(), "ht_refeicao", true, false, false,
					"Data: " + ht.getDataInclusao() + ", Refeicao Tipo: " + ht.getTipoRefeicao() + ", Calorias: "
							+ ht.getCalorias());
		}
	}

	public void alterar(ht_refeicao ht) {
		this.log = new LogGeralDAO();
		this.manager.merge(ht);
		this.log.criarLog("Usuário: " + ht.getIdCadastro(), "ht_refeicao", false, true, false,
				"Data: " + ht.getDataInclusao() + ", Refeicao Tipo: " + ht.getTipoRefeicao() + ", Calorias: "
						+ ht.getCalorias());
	}

	public void excluir(ht_refeicao ht) {
		this.log = new LogGeralDAO();
		Object c = this.manager.merge(ht);
		this.manager.remove(c);
		this.log.criarLog("Usuário: " + ht.getIdCadastro(), "ht_refeicao", false, false, true,
				"Data: " + ht.getDataInclusao() + ", Refeicao Tipo: " + ht.getTipoRefeicao() + ", Calorias: "
						+ ht.getCalorias());
	}

	public void salvar(ht_refeicao_tipo ht) {
		this.manager.persist(ht);
	}

	public void alterar(ht_refeicao_tipo ht) {
		this.manager.merge(ht);
	}

	public void excluir(ht_refeicao_tipo ht) {
		this.manager.remove(ht);
	}

	@SuppressWarnings("unchecked")
	public List<ht_refeicao> listarTodasRefeicoesDoUsuario(int idUsuario) {
		List<ht_refeicao> listarTabela = new ArrayList<>();
		this.query = this.manager.createNativeQuery(
				"select h.id, h.calorias, h.dataInclusao, h.tipoRefeicao as idRefeicao, h.idCadastro, "
						+ "a.tipoRefeicao as tipoRefeicao from healthTrack.dbo.ht_refeicao h inner join "
						+ "healthTrack.dbo.ht_refeicao_tipo a on a.id = h.tipoRefeicao where h.idCadastro = :p1 "
						+ "order by dataInclusao DESC ");
		this.query.setParameter("p1", idUsuario);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				ht_refeicao a = new ht_refeicao();
				if (obj[0] != null) {
					a.setId(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					a.setCalorias(Double.valueOf(obj[1].toString()));
				}
				if (obj[2] != null) {
					a.setDataInclusao((Date) obj[2]);
				}
				if (obj[3] != null) {
					a.setTipoRefeicao(Integer.valueOf(obj[3].toString()));
				}
				if (obj[4] != null) {
					a.setIdCadastro(Integer.valueOf(obj[4].toString()));
				}
				if (obj[5] != null) {
					a.setRefeicao(Integer.valueOf(obj[3].toString()) + " - " + obj[5].toString());
				}
				listarTabela.add(a);
			}
		}
		return listarTabela;
	}

	@SuppressWarnings("unchecked")
	public List<refeicao_join> listarDashboard(int idUsuario) {
		List<refeicao_join> listarTabela = new ArrayList<>();
		this.query = this.manager
				.createNativeQuery("select h.calorias, h.dataInclusao, h.tipoRefeicao as idRefeicao, h.idCadastro, "
						+ "a.tipoRefeicao as tipoRefeicao from healthTrack.dbo.ht_refeicao h inner join "
						+ "healthTrack.dbo.ht_refeicao_tipo a on a.id = h.tipoRefeicao where h.idCadastro = :p1 "
						+ "order by dataInclusao DESC ");
		this.query.setParameter("p1", idUsuario);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				refeicao_join a = new refeicao_join();
				if (obj[0] != null) {
					a.setCalorias(Double.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					a.setDataInclusão((Date) obj[1]);
				}
				if (obj[2] != null) {
					a.setTipoRefeicao(Integer.valueOf(obj[2].toString()));
				}
				if (obj[3] != null) {
					a.setIdCadastro(Integer.valueOf(obj[3].toString()));
				}
				if (obj[4] != null) {
					a.setRefeicao(obj[4].toString());
				}
				listarTabela.add(a);
			}
		}
		return listarTabela;
	}

	@SuppressWarnings("unchecked")
	public List<ht_refeicao> listarRefeicoesEspecifica(int idUsuario, int id) {
		List<ht_refeicao> listarTabela = new ArrayList<>();
		this.query = this.manager.createNativeQuery(
				"select h.id, h.calorias, h.dataInclusao, h.tipoRefeicao as idRefeicao, h.idCadastro, "
						+ "a.tipoRefeicao as tipoRefeicao from healthTrack.dbo.ht_refeicao h inner join "
						+ "healthTrack.dbo.ht_refeicao_tipo a on a.id = h.tipoRefeicao where h.idCadastro = :p1 and h.id = :p2 "
						+ "order by dataInclusao DESC ");
		this.query.setParameter("p1", idUsuario);
		this.query.setParameter("p2", id);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				ht_refeicao a = new ht_refeicao();
				if (obj[0] != null) {
					a.setId(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					a.setCalorias(Double.valueOf(obj[1].toString()));
				}
				if (obj[2] != null) {
					a.setDataInclusao((Date) obj[2]);
				}
				if (obj[3] != null) {
					a.setTipoRefeicao(Integer.valueOf(obj[3].toString()));
				}
				if (obj[4] != null) {
					a.setIdCadastro(Integer.valueOf(obj[4].toString()));
				}
				if (obj[5] != null) {
					a.setRefeicao(Integer.valueOf(obj[3].toString()) + " - " + obj[5].toString());
				}
				listarTabela.add(a);
			}
		}
		return listarTabela;
	}

	@SuppressWarnings("unchecked")
	public List<ht_refeicao_tipo> listarTiposDeRefeicoes() {
		this.query = this.manager.createQuery("from ht_refeicao_tipo ");
		return this.query.getResultList();
	}

}
