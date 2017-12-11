package br.com.ht.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.ht.entidades.ht_cadastro;
import br.com.ht.entidades.ht_imagens;
import br.com.ht.util.JPAUtil;

public class ImagemDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Query query;
	private EntityManager manager;

	public ImagemDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void salvar(ht_imagens ht) {
		if (ht.getId() == 0) {
			this.manager.persist(ht);
		} else {
			this.manager.merge(ht);
		}
	}

	public void excluir(ht_imagens ht) {
		this.manager.remove(ht);
	}

	@SuppressWarnings("unchecked")
	public byte[] pesquisaFoto(Integer id) {
		byte[] foto = null;
		if (this.manager.isOpen()) {
			this.query = this.manager.createQuery("from ht_imagens where id = :p1");
			this.query.setParameter("p1", id);
			List<ht_imagens> lista = this.query.getResultList();
			for (ht_imagens obj : lista) {
				if (obj != null) {
					foto = obj.getFoto();
				}
			}
		}
		return foto;
	}

	@SuppressWarnings("unchecked")
	public byte[] pesquisaFoto2(Integer id) {
		byte[] foto = null;
		if (this.manager.isOpen()) {
			this.query = this.manager.createQuery("from ht_imagens where idCadastro = :p1");
			this.query.setParameter("p1", id);
			List<ht_imagens> lista = this.query.getResultList();
			for (ht_imagens obj : lista) {
				if (obj != null) {
					foto = obj.getFoto();
				}
			}
		}
		return foto;
	}

	@SuppressWarnings("unchecked")
	public List<ht_imagens> listaFoto(ht_cadastro ht) {
		List<ht_imagens> listarTabela = new ArrayList<>();
		this.query = this.manager.createNativeQuery(
				"select top 1 id, foto, idCadastro from healthTrack.dbo.ht_imagens where idCadastro = :p1 order by id DESC ");
		this.query.setParameter("p1", ht.getId());
		List<Object[]> lista = this.query.getResultList();
		for (Object[] obj : lista) {
			ht_imagens i = new ht_imagens();
			if (obj[0] != null) {
				i.setId(Integer.valueOf(obj[0].toString()));
			}
			if (obj[1] != null) {
				i.setFoto(obj[1].toString().getBytes());
			}
			if (obj[2] != null) {
				i.setIdCadastro(Integer.valueOf(obj[2].toString()));
			}
			listarTabela.add(i);
		}
		return listarTabela;
	}
}
