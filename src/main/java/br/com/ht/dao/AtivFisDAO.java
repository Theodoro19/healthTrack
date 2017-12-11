package br.com.ht.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.ht.entidades.atividade_join;
import br.com.ht.entidades.ht_atividade_fisica;
import br.com.ht.entidades.ht_atividade_fisica_tipo;
import br.com.ht.util.JPAUtil;

public class AtivFisDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Query query;
	private EntityManager manager;
	private LogGeralDAO log;

	public AtivFisDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void salvar(ht_atividade_fisica ht) {
		if (ht.getId() == 0) {
			this.log = new LogGeralDAO();
			this.manager.persist(ht);
			this.log.criarLog("Usuário: " + ht.getIdCadastro(), "ht_atividade_fisica", true, false, false,
					"Data: " + ht.getDataInclusao() + ", Atividade Tipo: " + ht.getTipoAtividade() + ", Calorias: "
							+ ht.getCalorias());
		}
	}

	public void alterar(ht_atividade_fisica ht) {
		this.log = new LogGeralDAO();
		this.manager.merge(ht);
		this.log.criarLog("Usuário: " + ht.getIdCadastro(), "ht_atividade_fisica", false, true, false,
				"Data: " + ht.getDataInclusao() + ", Atividade Tipo: " + ht.getTipoAtividade() + ", Calorias: "
						+ ht.getCalorias());
	}

	public void excluir(ht_atividade_fisica ht) {
		this.log = new LogGeralDAO();
		Object c = this.manager.merge(ht);
		this.manager.remove(c);
		this.log.criarLog("Usuário: " + ht.getIdCadastro(), "ht_atividade_fisica", false, false, true,
				"Data: " + ht.getDataInclusao() + ", Atividade Tipo: " + ht.getTipoAtividade() + ", Calorias: "
						+ ht.getCalorias());
	}

	public void salvar(ht_atividade_fisica_tipo ht) {
		if (ht.getId() == 0) {
			this.manager.persist(ht);
		}
	}

	public void alterar(ht_atividade_fisica_tipo ht) {
		this.manager.merge(ht);
	}

	public void excluir(ht_atividade_fisica_tipo ht) {
		Object c = this.manager.merge(ht);
		this.manager.remove(c);
	}

	@SuppressWarnings("unchecked")
	public List<ht_atividade_fisica> listarTodasAtivFisDoUsuario(int idUsuario) {
		List<ht_atividade_fisica> listarTabela = new ArrayList<>();
		this.query = this.manager
				.createNativeQuery("select h.id, h.calorias, h.dataInclusao, h.tipoAtividade as idAtividade, "
						+ "h.idCadastro, a.tipoAtividade as nomeAtividade from healthTrack.dbo.ht_atividade_fisica h inner join "
						+ "healthTrack.dbo.ht_atividade_fisica_tipo a on a.id = h.tipoAtividade where h.idCadastro = :p1 "
						+ "order by dataInclusao DESC ");
		this.query.setParameter("p1", idUsuario);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				ht_atividade_fisica a = new ht_atividade_fisica();
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
					a.setTipoAtividade(Integer.valueOf(obj[3].toString()));
				}
				if (obj[4] != null) {
					a.setIdCadastro(Integer.valueOf(obj[4].toString()));
				}
				if (obj[5] != null) {
					a.setAtividade(Integer.valueOf(obj[3].toString()) + " - " + obj[5].toString());
				}
				listarTabela.add(a);
			}
		}
		return listarTabela;
	}

	@SuppressWarnings("unchecked")
	public List<ht_atividade_fisica> listarAtivFisEspecifica(int idUsuario, int id) {
		List<ht_atividade_fisica> listarTabela = new ArrayList<>();
		this.query = this.manager
				.createNativeQuery("select h.id, h.calorias, h.dataInclusao, h.tipoAtividade as idAtividade, "
						+ "h.idCadastro, a.tipoAtividade as nomeAtividade from healthTrack.dbo.ht_atividade_fisica h inner join "
						+ "healthTrack.dbo.ht_atividade_fisica_tipo a on a.id = h.tipoAtividade where h.idCadastro = :p1 and h.id = :p2 "
						+ "order by dataInclusao DESC ");
		this.query.setParameter("p1", idUsuario);
		this.query.setParameter("p2", id);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				ht_atividade_fisica a = new ht_atividade_fisica();
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
					a.setTipoAtividade(Integer.valueOf(obj[3].toString()));
				}
				if (obj[4] != null) {
					a.setIdCadastro(Integer.valueOf(obj[4].toString()));
				}
				if (obj[5] != null) {
					a.setAtividade(Integer.valueOf(obj[3].toString()) + " - " + obj[5].toString());
				}
				listarTabela.add(a);
			}
		}
		return listarTabela;
	}

	@SuppressWarnings("unchecked")
	public List<ht_atividade_fisica_tipo> listarTiposDeAtividade() {
		this.query = this.manager.createQuery("from ht_atividade_fisica_tipo ");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<atividade_join> listaDashboard(int idUsuario) {
		List<atividade_join> listarTabela = new ArrayList<>();
		this.query = this.manager
				.createNativeQuery("select top 10 h.calorias, h.dataInclusao, h.tipoAtividade as idAtividade, "
						+ "h.idCadastro, a.tipoAtividade as nomeAtividade from healthTrack.dbo.ht_atividade_fisica h inner join "
						+ "healthTrack.dbo.ht_atividade_fisica_tipo a on a.id = h.tipoAtividade where h.idCadastro = :p1 "
						+ "order by dataInclusao DESC ");
		this.query.setParameter("p1", idUsuario);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				atividade_join a = new atividade_join();
				if (obj[0] != null) {
					a.setCalorias(Double.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					a.setDataInclusão((Date) obj[1]);
				}
				if (obj[2] != null) {
					a.setIdAtividade(Integer.valueOf(obj[2].toString()));
				}
				if (obj[3] != null) {
					a.setIdCadastro(Integer.valueOf(obj[3].toString()));
				}
				if (obj[4] != null) {
					a.setAtividade(obj[4].toString());
				}
				listarTabela.add(a);
			}
		}
		return listarTabela;
	}

}
