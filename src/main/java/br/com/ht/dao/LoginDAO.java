package br.com.ht.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.ht.entidades.ht_cadastro;
import br.com.ht.util.JPAUtil;

public class LoginDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager manager;
	private Query query;
	
	public LoginDAO() {
		this.manager = JPAUtil.getManager();
	}
	
	public List<ht_cadastro> buscaTodos(ht_cadastro login) {
		return this.manager.createQuery("from ht_cadastro where email = '"+login.getEmail()+"' and senha = '"+login.getSenha()+"'", ht_cadastro.class).getResultList();
	}
	
	public List<ht_cadastro> esqueceuSenha(ht_cadastro login) {
		return this.manager.createQuery("from ht_cadastro where email = '"+login.getEmail()+"'", ht_cadastro.class).getResultList();
	}
	
	public void updateSenha(ht_cadastro senha){
		this.query = this.manager.createQuery("update ht_cadastro set senha = :p1 where email = :p2");
		this.query.setParameter("p1", senha.getSenha());
		this.query.setParameter("p2", senha.getEmail());
		this.query.executeUpdate();
	}
}
