package br.com.ht.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.ht.dao.RefeicaoDAO;
import br.com.ht.entidades.ht_refeicao;
import br.com.ht.entidades.ht_refeicao_tipo;
import br.com.ht.entidades.refeicao_join;

@ManagedBean(name = "refeicao")
@ViewScoped
public class RefeicaoBEAN implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// DEPEND�NCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	// OBJETOS
	private RefeicaoDAO rDAO;
	private ht_refeicao refeicao;
	private ht_refeicao_tipo refeicaoTipo;
	// ATRIBUTOS
	private List<ht_refeicao> listaRefeicao;
	private List<ht_refeicao_tipo> listaRefeicaoTipo;
	private List<refeicao_join> dashboard;

	// GET X SET
	public RefeicaoDAO getrDAO() {
		return rDAO;
	}

	public void setrDAO(RefeicaoDAO rDAO) {
		this.rDAO = rDAO;
	}

	public ht_refeicao getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(ht_refeicao refeicao) {
		this.refeicao = refeicao;
	}

	public ht_refeicao_tipo getRefeicaoTipo() {
		return refeicaoTipo;
	}

	public void setRefeicaoTipo(ht_refeicao_tipo refeicaoTipo) {
		this.refeicaoTipo = refeicaoTipo;
	}

	public List<ht_refeicao> getListaRefeicao() {
		this.rDAO = new RefeicaoDAO();
		this.listaRefeicao = this.rDAO.listarTodasRefeicoesDoUsuario(this.sessaoMB.getUsuario().getId());
		return listaRefeicao;
	}

	public void setListaRefeicao(List<ht_refeicao> listaRefeicao) {
		this.listaRefeicao = listaRefeicao;
	}

	public List<ht_refeicao_tipo> getListaRefeicaoTipo() {
		this.rDAO = new RefeicaoDAO();
		this.listaRefeicaoTipo = this.rDAO.listarTiposDeRefeicoes();
		return listaRefeicaoTipo;
	}

	public void setListaRefeicaoTipo(List<ht_refeicao_tipo> listaRefeicaoTipo) {
		this.listaRefeicaoTipo = listaRefeicaoTipo;
	}

	public List<refeicao_join> getDashboard() {
		this.rDAO = new RefeicaoDAO();
		if (this.sessaoMB.getUsuario() != null && this.sessaoMB.isLogado() == true) {
			if (this.sessaoMB.getUsuario().getPermissao().equals("Usuário")) {
				this.dashboard = this.rDAO.listarDashboard(this.sessaoMB.getUsuario().getId());
			}
		} else {
			this.dashboard = null;
		}
		return dashboard;
	}

	public void setDashboard(List<refeicao_join> dashboard) {
		this.dashboard = dashboard;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	// METODOS

	public void salvar() {
		this.rDAO = new RefeicaoDAO();
		this.refeicao.setCalorias(this.refeicao.getCalorias());
		this.refeicao.setDataInclusao(this.refeicao.getDataInclusao());
		this.refeicao.setIdCadastro(this.sessaoMB.getUsuario().getId());
		this.refeicao.setTipoRefeicao(this.refeicao.getTipoRefeicao());
		this.rDAO.salvar(this.refeicao);
	}

	public void alterar() {
		this.rDAO = new RefeicaoDAO();
		this.refeicao.setCalorias(this.refeicao.getCalorias());
		this.refeicao.setDataInclusao(this.refeicao.getDataInclusao());
		this.refeicao.setIdCadastro(this.sessaoMB.getUsuario().getId());
		this.refeicao.setTipoRefeicao(this.refeicao.getTipoRefeicao());
		this.rDAO.alterar(this.refeicao);
	}

	public void excluir(ht_refeicao ht) {
		this.rDAO = new RefeicaoDAO();
		this.rDAO.excluir(ht);
		this.msgExcluido();
	}

	public void carregar(ht_refeicao ht) {
		this.refeicao.setCalorias(ht.getCalorias());
		this.refeicao.setDataInclusao(ht.getDataInclusao());
		this.refeicao.setIdCadastro(ht.getIdCadastro());
		this.refeicao.setTipoRefeicao(ht.getTipoRefeicao());
		this.refeicao.setId(ht.getId());
	}

	public void limpar() {
		this.refeicao = new ht_refeicao();
	}

	public void salvarTipo() {
		this.rDAO = new RefeicaoDAO();
		this.refeicaoTipo.setTipoRefeicao(this.refeicaoTipo.getTipoRefeicao());
		this.rDAO.salvar(this.refeicaoTipo);
	}

	public void excluirTipo(ht_refeicao_tipo ht) {
		this.rDAO = new RefeicaoDAO();
		this.rDAO.excluir(ht);
	}

	public void carregarDialogTipo(ht_refeicao_tipo ht) {
		this.refeicaoTipo.setId(ht.getId());
		this.refeicaoTipo.setTipoRefeicao(ht.getTipoRefeicao());
	}

	public void limparTipo() {
		this.refeicaoTipo = new ht_refeicao_tipo();
	}

	public void msgExcluido() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluído!", "Excluído!"));
	}

	public void redirecionar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		NavigationHandler nh = fc.getApplication().getNavigationHandler();
		nh.handleNavigation(fc, null, "/refeicao/refeicao.xhtml?faces-redirect=true");
	}
}
