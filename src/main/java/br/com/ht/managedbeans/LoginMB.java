package br.com.ht.managedbeans;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;

import br.com.ht.dao.CadastroDAO;
import br.com.ht.dao.LoginDAO;
import br.com.ht.entidades.ht_cadastro;

@ViewScoped
@ManagedBean(name = "login")
public class LoginMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	// OBJETOS
	private ht_cadastro usuario = new ht_cadastro();
	private LoginDAO ldao;

	// ATRIBUTOS
	private List<ht_cadastro> listaUsuario;
	private String senha;

	// GET X SET
	public ht_cadastro getUsuario() {
		return usuario;
	}

	public void setUsuario(ht_cadastro usuario) {
		this.usuario = usuario;
	}

	public List<ht_cadastro> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<ht_cadastro> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	// METODOS
	public String login() throws NoSuchAlgorithmException {
		this.ldao = new LoginDAO();
		this.usuario.setSenha(this.criptografar(this.usuario.getSenha()));
		if (this.listaUsuario == null) {
			this.listaUsuario = this.ldao.buscaTodos(this.usuario);
			for (ht_cadastro user : listaUsuario) {
				this.usuario = user;
			}
		}
		if (!this.listaUsuario.isEmpty()) {
			this.sessaoMB.setUsuario(this.usuario);
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.setAttribute("usuario", this.usuario.getEmail());
			session.setAttribute("userName", this.usuario.getNome());
			this.sessaoMB.setIpUser(Faces.getRemoteAddr());
			this.sessaoMB.setLogado(true);
			return "/dashboard.xhtml?faces-redirect=true";
		} else {
			this.listaUsuario = null;
			this.usuario.setSenha("");
			this.msgLoginInvalido();
		}
		return null;
	}

	public String esqueceuSenha() throws NoSuchAlgorithmException {
		CadastroDAO c = new CadastroDAO();
		this.usuario.setSenha(this.criptografar(this.usuario.getSenha()));
		this.usuario.setEmail(this.usuario.getEmail());
		int id = c.retornaId(this.usuario.getEmail());
		if (id != 0) {
			this.usuario.setId(id);
			c.alterarSenha(this.usuario);
			return "/login.xhtml?faces-redirect=true";
		} else {
			this.msgCadastro();
			return null;
		}
	}

	public void salvarCookies() {
		try {
			Cookie email = new Cookie("email", this.usuario.getEmail());
			Cookie senha = new Cookie("senha", this.usuario.getSenha());
			email.setMaxAge(3600);
			senha.setMaxAge(3600);
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
			response.addCookie(email);
			response.addCookie(senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluirCookies() {
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
			HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie ck : cookies) {
					if (ck.getName().equalsIgnoreCase("email")) {
						ck.setMaxAge(0);
						response.addCookie(ck);
					}
					if (ck.getName().equalsIgnoreCase("senha")) {
						ck.setMaxAge(0);
						response.addCookie(ck);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logout() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.removeAttribute("usuario");
		this.sessaoMB.setLogado(false);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		NavigationHandler nh = fc.getApplication().getNavigationHandler();
		nh.handleNavigation(fc, null, "/login.xhtml?faces-redirect=true");
	}

	public String criptografar(String user) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5"); // or "SHA -1"
		md.update(user.getBytes());
		BigInteger hash = new BigInteger(1, md.digest());
		String senhaCriptografada = hash.toString(16);
		while (senhaCriptografada.length() < 32) { // 40 for SHA -1
			senhaCriptografada = "0" + senhaCriptografada;
		}
		return senhaCriptografada;
	}

	public void msgLoginInvalido() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Login ou Senha incorreto!"));
	}

	public void msgLoginNaoEncontrado() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Usuário não encontrado!"));
	}

	public void msgCadastro() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "", "E-mail não cadastrado!"));
	}

}
