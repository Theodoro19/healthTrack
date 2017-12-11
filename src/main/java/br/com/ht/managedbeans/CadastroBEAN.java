package br.com.ht.managedbeans;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import br.com.ht.dao.CadastroDAO;
import br.com.ht.dao.ImagemDAO;
import br.com.ht.entidades.ht_cadastro;
import br.com.ht.entidades.ht_imagens;

@ManagedBean(name = "cadastro")
@ViewScoped
public class CadastroBEAN implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// DEPEND�NCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private CadastroDAO cDAO;
	private ht_cadastro cadastro = new ht_cadastro();
	private ht_cadastro cadastroBEAN = new ht_cadastro();
	private ht_imagens imagem = new ht_imagens();

	// ATRIBUTOS
	private List<ht_cadastro> listaUsuario, listaAdministrador;
	private boolean alterarSenha, enviarFoto;

	// GET X SET
	public CadastroDAO getcDAO() {
		return cDAO;
	}

	public void setcDAO(CadastroDAO cDAO) {
		this.cDAO = cDAO;
	}

	public ht_cadastro getCadastro() {
		return cadastro;
	}

	public void setCadastro(ht_cadastro cadastro) {
		this.cadastro = cadastro;
	}

	public ht_cadastro getCadastroBEAN() {
		return cadastroBEAN;
	}

	public void setCadastroBEAN(ht_cadastro cadastroBEAN) {
		this.cadastroBEAN = cadastroBEAN;
	}

	public ht_imagens getImagem() {
		return imagem;
	}

	public void setImagem(ht_imagens imagem) {
		this.imagem = imagem;
	}

	public List<ht_cadastro> getListaUsuario() {
		this.cDAO = new CadastroDAO();
		this.listaUsuario = this.cDAO.listarUsuariosCadastrados();
		return listaUsuario;
	}

	public void setListaUsuario(List<ht_cadastro> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public List<ht_cadastro> getListaAdministrador() {
		if (this.listaAdministrador == null) {
			this.cDAO = new CadastroDAO();
			this.listaAdministrador = this.cDAO.listarAdminCadastrados();
		}
		return listaAdministrador;
	}

	public void setListaAdministrador(List<ht_cadastro> listaAdministrador) {
		this.listaAdministrador = listaAdministrador;
	}

	public boolean isAlterarSenha() {
		return alterarSenha;
	}

	public void setAlterarSenha(boolean alterarSenha) {
		this.alterarSenha = alterarSenha;
	}

	public boolean isEnviarFoto() {
		return enviarFoto;
	}

	public void setEnviarFoto(boolean enviarFoto) {
		this.enviarFoto = enviarFoto;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	// METODOS

	public void salvar() {
		try {
			this.cDAO = new CadastroDAO();
			boolean x = this.cDAO.verificarEmailCadastrado(this.cadastro.getEmail());
			if (x == false) {
				CriptografaSenha cs = new CriptografaSenha();
				this.cadastro.setAltura(this.cadastro.getAltura());
				this.cadastro.setDataCadastro(new Date());
				this.cadastro.setDataNascimento(this.cadastro.getDataNascimento());
				this.cadastro.setEmail(this.cadastro.getEmail());
				this.cadastro.setNome(this.cadastro.getNome());
				this.cadastro.setPesoInicial(this.cadastro.getPesoInicial());
				this.cadastro.setSenha(cs.retornaSenhaCript(String.valueOf(this.cadastro.getSenha())));
				this.cadastro.setSexo(this.cadastro.getSexo());
				this.cadastro.setSobrenome(this.cadastro.getSobrenome());
				this.cadastro.setPermissao("Usuário");
				this.cDAO.salvar(this.cadastro);
			} else {
				this.msgEmail();
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public void administradorSalvar() {
		try {
			this.cDAO = new CadastroDAO();
			boolean x = this.cDAO.verificarEmailCadastrado(this.cadastro.getEmail());
			if (x == false) {
				CriptografaSenha cs = new CriptografaSenha();
				this.cadastro.setAltura(this.cadastro.getAltura());
				this.cadastro.setDataCadastro(new Date());
				this.cadastro.setDataNascimento(this.cadastro.getDataNascimento());
				this.cadastro.setEmail(this.cadastro.getEmail());
				this.cadastro.setNome(this.cadastro.getNome());
				this.cadastro.setPesoInicial(this.cadastro.getPesoInicial());
				this.cadastro.setSenha(cs.retornaSenhaCript(String.valueOf(this.cadastro.getSenha())));
				this.cadastro.setSexo(this.cadastro.getSexo());
				this.cadastro.setSobrenome(this.cadastro.getSobrenome());
				this.cadastro.setPermissao("Admin");
				this.cDAO.salvar(this.cadastro);
			} else {
				this.msgEmail();
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public void alterar() {
		try {
			this.cDAO = new CadastroDAO();
			CriptografaSenha cs = new CriptografaSenha();
			this.cadastro.setAltura(this.cadastro.getAltura());
			this.cadastro.setDataCadastro(new Date());
			this.cadastro.setDataNascimento(this.cadastro.getDataNascimento());
			this.cadastro.setEmail(this.cadastro.getEmail());
			this.cadastro.setNome(this.cadastro.getNome());
			this.cadastro.setPesoInicial(this.cadastro.getPesoInicial());
			if (this.alterarSenha == true) {
				this.cadastro.setSenha(cs.retornaSenhaCript(String.valueOf(this.cadastro.getSenha())));
			}
			this.cadastro.setSexo(this.cadastro.getSexo());
			this.cadastro.setSobrenome(this.cadastro.getSobrenome());
			this.cadastro.setPermissao("Usuário");
			this.cDAO.alterar(this.cadastro);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public void administradorAlterar() {
		try {
			this.cDAO = new CadastroDAO();
			CriptografaSenha cs = new CriptografaSenha();
			this.cadastro.setAltura(this.cadastro.getAltura());
			this.cadastro.setDataCadastro(new Date());
			this.cadastro.setDataNascimento(this.cadastro.getDataNascimento());
			this.cadastro.setEmail(this.cadastro.getEmail());
			this.cadastro.setNome(this.cadastro.getNome());
			this.cadastro.setPesoInicial(this.cadastro.getPesoInicial());
			if (this.alterarSenha == true) {
				this.cadastro.setSenha(cs.retornaSenhaCript(String.valueOf(this.cadastro.getSenha())));
			}
			this.cadastro.setSexo(this.cadastro.getSexo());
			this.cadastro.setSobrenome(this.cadastro.getSobrenome());
			this.cadastro.setPermissao("Admin");
			this.cDAO.alterar(this.cadastro);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public void alterarSenha() {
		try {
			this.cDAO = new CadastroDAO();
			CriptografaSenha cs = new CriptografaSenha();
			this.cadastro = this.sessaoMB.getUsuario();
			this.cadastro.setSenha(cs.retornaSenhaCript(this.cadastroBEAN.getSenha()));
			this.cDAO.alterarSenha(this.cadastro);
			this.msgAlterado();
			this.alterarSenha = false;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public void salvarFoto(FileUploadEvent event) {
		if (event.getFile().getContents() != null) {
			ImagemDAO im = new ImagemDAO();
			this.imagem.setFoto(event.getFile().getContents());
			this.imagem.setIdCadastro(this.sessaoMB.getUsuario().getId());
			im.salvar(this.imagem);
			this.msgAlterado();
			this.alterarSenha = false;
		}
	}

	public void excluirFoto() {
		ImagemDAO im = new ImagemDAO();
		im.excluir(this.sessaoMB.getImagem());
	}

	public void excluir(ht_cadastro ht) {
		this.cDAO = new CadastroDAO();
		this.cDAO.excluir(ht);
	}

	public void pesquisaIdSelecionado() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Map<String, String> params = ctx.getExternalContext().getRequestParameterMap();
		String id = params.get("id");
		if (id != null) {
			this.cDAO = new CadastroDAO();
			ImagemDAO im = new ImagemDAO();
			this.cadastro = this.cDAO.pesquisaIdPorCodigo(Integer.valueOf(id));
			List<ht_imagens> l = im.listaFoto(this.cadastro);
			if (l != null && l.size() > 0) {
				this.imagem = l.get(0);
			} else {
				this.imagem = null;
			}
		}
	}

	public void redirecionar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		NavigationHandler nh = fc.getApplication().getNavigationHandler();
		nh.handleNavigation(fc, null, "/dashboard.xhtml?faces-redirect=true");
	}
	
	public void novoCadastro() {
		FacesContext fc = FacesContext.getCurrentInstance();
		NavigationHandler nh = fc.getApplication().getNavigationHandler();
		nh.handleNavigation(fc, null, "/login.xhtml?faces-redirect=true");
	}

	public void msgEmail() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Email já cadastrado!"));
	}

	public void msgAlterado() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado!", "Alterado!"));
	}
}
