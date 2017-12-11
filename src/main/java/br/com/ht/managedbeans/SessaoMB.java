package br.com.ht.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.ht.dao.ImagemDAO;
import br.com.ht.entidades.ht_cadastro;
import br.com.ht.entidades.ht_imagens;

@SessionScoped
@ManagedBean(name = "SessaoMB")
public class SessaoMB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ht_cadastro usuario = new ht_cadastro();
	private ht_imagens imagem = new ht_imagens();
	private boolean logado;
	private List<ht_cadastro> listaUsuario;
	private List<ht_imagens> listaImagem;
	private String ipUser;

	public ht_cadastro getUsuario() {
		return usuario;
	}

	public void setUsuario(ht_cadastro usuario) {
		this.usuario = usuario;
	}

	public ht_imagens getImagem() {
		return imagem;
	}

	public void setImagem(ht_imagens imagem) {
		this.imagem = imagem;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public List<ht_cadastro> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<ht_cadastro> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public List<ht_imagens> getListaImagem() {
		ImagemDAO im = new ImagemDAO();
		this.listaImagem = im.listaFoto(this.usuario);
		return listaImagem;
	}

	public void setListaImagem(List<ht_imagens> listaImagem) {
		this.listaImagem = listaImagem;
	}

	public String getIpUser() {
		return ipUser;
	}

	public void setIpUser(String ipUser) {
		this.ipUser = ipUser;
	}

	public boolean verificarPessoa(String nome) {
		boolean valida = false;
		if (this.usuario.getPermissao() != null) {
			if (nome.equals(this.usuario.getPermissao())) {
				valida = true;
			}
		}
		return valida;
	}

}
