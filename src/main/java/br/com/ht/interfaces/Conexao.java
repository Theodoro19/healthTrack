package br.com.ht.interfaces;

import javax.persistence.EntityManager;

public interface Conexao {

	EntityManager getConexao();
}
