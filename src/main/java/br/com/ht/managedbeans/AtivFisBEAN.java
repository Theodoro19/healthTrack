package br.com.ht.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.ht.dao.AtivFisDAO;
import br.com.ht.entidades.atividade_join;
import br.com.ht.entidades.ht_atividade_fisica;
import br.com.ht.entidades.ht_atividade_fisica_tipo;

@ManagedBean(name = "atividade")
@ViewScoped
public class AtivFisBEAN implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	// OBJETOS
	private AtivFisDAO aDAO;
	private ht_atividade_fisica atividade = new ht_atividade_fisica();
	private ht_atividade_fisica_tipo ativTipo = new ht_atividade_fisica_tipo();
	// ATRIBUTOS
	private List<ht_atividade_fisica> listaAtividade;
	private List<ht_atividade_fisica_tipo> listarAtivTipo;
	private List<atividade_join> dashboard;

	// GET X SET
	public AtivFisDAO getaDAO() {
		return aDAO;
	}

	public void setaDAO(AtivFisDAO aDAO) {
		this.aDAO = aDAO;
	}

	public ht_atividade_fisica getAtividade() {
		return atividade;
	}

	public void setAtividade(ht_atividade_fisica atividade) {
		this.atividade = atividade;
	}

	public ht_atividade_fisica_tipo getAtivTipo() {
		return ativTipo;
	}

	public void setAtivTipo(ht_atividade_fisica_tipo ativTipo) {
		this.ativTipo = ativTipo;
	}

	public List<ht_atividade_fisica> getListaAtividade() {
		this.aDAO = new AtivFisDAO();
		this.listaAtividade = this.aDAO.listarTodasAtivFisDoUsuario(this.sessaoMB.getUsuario().getId());
		return listaAtividade;
	}

	public void setListaAtividade(List<ht_atividade_fisica> listaAtividade) {
		this.listaAtividade = listaAtividade;
	}

	public List<ht_atividade_fisica_tipo> getListarAtivTipo() {
		this.aDAO = new AtivFisDAO();
		this.listarAtivTipo = this.aDAO.listarTiposDeAtividade();
		return listarAtivTipo;
	}

	public void setListarAtivTipo(List<ht_atividade_fisica_tipo> listarAtivTipo) {
		this.listarAtivTipo = listarAtivTipo;
	}

	public List<atividade_join> getDashboard() {
		this.aDAO = new AtivFisDAO();
		if (this.sessaoMB.getUsuario() != null && this.sessaoMB.isLogado() == true) {
			if (this.sessaoMB.getUsuario().getPermissao().equals("Usuário")) {
				this.dashboard = this.aDAO.listaDashboard(this.sessaoMB.getUsuario().getId());
			}
		} else {
			this.dashboard = null;
		}
		return dashboard;
	}

	public void setDashboard(List<atividade_join> dashboard) {
		this.dashboard = dashboard;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	// METODOS

	public void salvar() {
		this.aDAO = new AtivFisDAO();
		this.atividade.setCalorias(this.atividade.getCalorias());
		this.atividade.setDataInclusao(this.atividade.getDataInclusao());
		this.atividade.setIdCadastro(this.sessaoMB.getUsuario().getId());
		this.atividade.setTipoAtividade(this.atividade.getTipoAtividade());
		this.aDAO.salvar(this.atividade);
		this.atividade = new ht_atividade_fisica();
	}

	public void alterar() {
		this.aDAO = new AtivFisDAO();
		this.atividade.setCalorias(this.atividade.getCalorias());
		this.atividade.setDataInclusao(this.atividade.getDataInclusao());
		this.atividade.setIdCadastro(this.sessaoMB.getUsuario().getId());
		this.atividade.setTipoAtividade(this.atividade.getTipoAtividade());
		this.aDAO.alterar(this.atividade);
		this.atividade = new ht_atividade_fisica();
	}

	public void excluir(ht_atividade_fisica ht) {
		this.aDAO = new AtivFisDAO();
		this.aDAO.excluir(ht);
		this.msgExcluido();
	}

	public void carregar(ht_atividade_fisica ht) {
		this.atividade.setCalorias(ht.getCalorias());
		this.atividade.setDataInclusao(ht.getDataInclusao());
		this.atividade.setIdCadastro(ht.getIdCadastro());
		this.atividade.setTipoAtividade(ht.getTipoAtividade());
		this.atividade.setId(ht.getId());
	}

	public void limpar() {
		this.atividade = new ht_atividade_fisica();
	}

	public void salvarTipo() {
		this.aDAO = new AtivFisDAO();
		this.ativTipo.setTipoAtividade(this.ativTipo.getTipoAtividade());
		this.aDAO.salvar(this.ativTipo);
		this.ativTipo = new ht_atividade_fisica_tipo();
	}

	public void excluirTipo(ht_atividade_fisica_tipo ht) {
		this.aDAO = new AtivFisDAO();
		this.aDAO.excluir(ht);
		this.msgExcluido();
	}

	public void carregarDialogTipo(ht_atividade_fisica_tipo ht) {
		this.ativTipo.setId(ht.getId());
		this.ativTipo.setTipoAtividade(ht.getTipoAtividade());
	}

	public void limparTipo() {
		this.ativTipo = new ht_atividade_fisica_tipo();
	}

	public void msgExcluido() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluído!", "Excluído!"));
	}

	public void redirecionar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		NavigationHandler nh = fc.getApplication().getNavigationHandler();
		nh.handleNavigation(fc, null, "/atividade/atividade-fisica.xhtml?faces-redirect=true");
	}
}
