package br.com.ht.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.ht.entidades.ht_cadastro;
import br.com.ht.util.JPAUtil;

public class CadastroDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Query query;
	private EntityManager manager;
	private LogGeralDAO log;

	public CadastroDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void salvar(ht_cadastro ht) {
		if (ht.getId() == 0) {
			this.log = new LogGeralDAO();
			this.manager.persist(ht);
			this.log.criarLog("Usuário: " + ht.getId(), "ht_cadastro", true, false, false,
					"Data: " + ht.getDataCadastro());
		}
	}

	public void alterar(ht_cadastro ht) {
		this.log = new LogGeralDAO();
		this.manager.merge(ht);
		this.log.criarLog("Usuário: " + ht.getId(), "ht_cadastro", false, true, false, "Data: " + ht.getDataCadastro());
	}

	public void alterarSenha(ht_cadastro ht) {
		this.query = this.manager
				.createNativeQuery("update healthTrack.dbo.ht_cadastro set senha = :p1 where id = :p2 ");
		this.query.setParameter("p1", ht.getSenha());
		this.query.setParameter("p2", ht.getId());
		this.query.executeUpdate();
	}

	public void excluir(ht_cadastro ht) {
		this.log = new LogGeralDAO();
		Object c = this.manager.merge(ht);
		this.manager.remove(c);
		this.log.criarLog("Usuário: " + ht.getId(), "ht_cadastro", false, false, true, "Data: " + ht.getDataCadastro());
	}

	public ht_cadastro pesquisaIdPorCodigo(int id) {
		return this.manager.find(ht_cadastro.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<ht_cadastro> listarUsuariosCadastrados() {
		this.query = this.manager.createQuery("from ht_cadastro where permissao = 'Usuário' order by dataCadastro ");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ht_cadastro> listarAdminCadastrados() {
		this.query = this.manager.createQuery("from ht_cadastro where permissao = 'Admin' order by dataCadastro ");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ht_cadastro> listarUsuarioEspecifico(int id) {
		this.query = this.manager.createQuery("from ht_cadastro where id = :p1 ");
		this.query.setParameter("p1", id);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public boolean verificarEmailCadastrado(String email) {
		boolean x = false;
		this.query = this.manager.createQuery("from ht_cadastro where email = :p1 ");
		this.query.setParameter("p1", email);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			x = true;
		}
		return x;
	}

	@SuppressWarnings("unchecked")
	public int retornaId(String email) {
		int x = 0;
		this.query = this.manager.createNativeQuery("select id from healthTrack.dbo.ht_cadastro where email = :p1 ");
		this.query.setParameter("p1", email);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object is : lista) {
				x = Integer.valueOf(String.valueOf(is));
			}
		}
		return x;

	}
}
