package br.com.ht.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.ht.entidades.ht_pressao;

import br.com.ht.dao.PressaoDAO;

@ManagedBean(name = "pressao")
@ViewScoped
public class PressaoBEAN implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	// OBJETOS
	private PressaoDAO pDAO;
	private ht_pressao pressao = new ht_pressao();
	// ATRIBUTOS
	private List<ht_pressao> listaPressao, dashboard;

	// GET X SET
	public PressaoDAO getpDAO() {
		return pDAO;
	}

	public void setpDAO(PressaoDAO pDAO) {
		this.pDAO = pDAO;
	}

	public ht_pressao getPressao() {
		return pressao;
	}

	public void setPressao(ht_pressao pressao) {
		this.pressao = pressao;
	}

	public List<ht_pressao> getDashboard() {
		this.pDAO = new PressaoDAO();
		if (this.sessaoMB.getUsuario() != null && this.sessaoMB.isLogado() == true) {
			if (this.sessaoMB.getUsuario().getPermissao().equals("Usuário")) {
				this.dashboard = this.pDAO.listarDashboard(this.sessaoMB.getUsuario().getId());
			}
		} else {
			this.dashboard = null;
		}
		return dashboard;
	}

	public void setDashboard(List<ht_pressao> dashboard) {
		this.dashboard = dashboard;
	}

	public List<ht_pressao> getListaPressao() {
		this.pDAO = new PressaoDAO();
		this.listaPressao = this.pDAO.listarTodasPressoesDoUsuario(this.sessaoMB.getUsuario().getId());
		return listaPressao;
	}

	public void setListaPressao(List<ht_pressao> listaPressao) {
		this.listaPressao = listaPressao;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	// METODOS

	public void salvar() {
		this.pDAO = new PressaoDAO();
		this.pressao.setDataInclusao(this.pressao.getDataInclusao());
		this.pressao.setIdCadastro(this.pressao.getIdCadastro());
		this.pressao.setPrimeiroValor(this.pressao.getPrimeiroValor());
		this.pressao.setSegundoValor(this.pressao.getSegundoValor());
		this.pDAO.salvar(this.pressao);
	}

	public void alterar() {
		this.pDAO = new PressaoDAO();
		this.pressao.setDataInclusao(this.pressao.getDataInclusao());
		this.pressao.setIdCadastro(this.pressao.getIdCadastro());
		this.pressao.setPrimeiroValor(this.pressao.getPrimeiroValor());
		this.pressao.setSegundoValor(this.pressao.getSegundoValor());
		this.pDAO.alterar(this.pressao);
	}

	public void excluir(ht_pressao ht) {
		this.pDAO = new PressaoDAO();
		this.pDAO.excluir(ht);
		this.msgExcluido();
	}

	public void carregar(ht_pressao ht) {
		this.pressao.setDataInclusao(ht.getDataInclusao());
		this.pressao.setIdCadastro(ht.getIdCadastro());
		this.pressao.setPrimeiroValor(ht.getPrimeiroValor());
		this.pressao.setSegundoValor(ht.getSegundoValor());
		this.pressao.setId(ht.getId());
	}

	public void limpar() {
		this.pressao = new ht_pressao();
	}

	public void msgExcluido() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluído!", "Excluído!"));
	}

	public void redirecionar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		NavigationHandler nh = fc.getApplication().getNavigationHandler();
		nh.handleNavigation(fc, null, "/pressao/pressao.xhtml?faces-redirect=true");
	}
}
