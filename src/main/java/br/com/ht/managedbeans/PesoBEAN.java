package br.com.ht.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.ht.dao.PesoDAO;
import br.com.ht.entidades.ht_peso;

@ManagedBean(name = "peso")
@ViewScoped
public class PesoBEAN implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	// OBJETOS
	private PesoDAO pDAO;
	private ht_peso peso = new ht_peso();
	// ATRIBUTOS
	private List<ht_peso> listarPesoUsuario, dashboard;

	// GET X SET
	public PesoDAO getpDAO() {
		return pDAO;
	}

	public void setpDAO(PesoDAO pDAO) {
		this.pDAO = pDAO;
	}

	public ht_peso getPeso() {
		return peso;
	}

	public void setPeso(ht_peso peso) {
		this.peso = peso;
	}

	public List<ht_peso> getListarPesoUsuario() {
		this.pDAO = new PesoDAO();
		this.listarPesoUsuario = this.pDAO.listarTodosPesosDoUsuario(this.sessaoMB.getUsuario().getId());
		return listarPesoUsuario;
	}

	public void setListarPesoUsuario(List<ht_peso> listarPesoUsuario) {
		this.listarPesoUsuario = listarPesoUsuario;
	}

	public List<ht_peso> getDashboard() {
		this.pDAO = new PesoDAO();
		if (this.sessaoMB.getUsuario() != null && this.sessaoMB.isLogado() == true) {
			if (this.sessaoMB.getUsuario().getPermissao().equals("Usuário")) {
				List<ht_peso> lista = this.pDAO.listarDashboard(this.sessaoMB.getUsuario().getId());
				this.dashboard = new ArrayList<>();
				for (ht_peso ht : lista) {
					double altura = this.sessaoMB.getUsuario().getAltura();
					double imc = (ht.getPeso() / (altura * altura))*10000;
					ht.setImc(imc);
					this.dashboard.add(ht);
				}
			}
		} else {
			this.dashboard = null;
		}
		return dashboard;
	}

	public void setDashboard(List<ht_peso> dashboard) {
		this.dashboard = dashboard;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	// METODOS

	public void salvar() {
		this.pDAO = new PesoDAO();
		this.peso.setDataAlteracao(this.peso.getDataAlteracao());
		this.peso.setPeso(this.peso.getPeso());
		this.peso.setIdCadastro(this.sessaoMB.getUsuario().getId());
		this.pDAO.salvar(this.peso);
	}

	public void alterar() {
		this.pDAO = new PesoDAO();
		this.peso.setDataAlteracao(this.peso.getDataAlteracao());
		this.peso.setPeso(this.peso.getPeso());
		this.peso.setIdCadastro(this.sessaoMB.getUsuario().getId());
		this.pDAO.alterar(this.peso);
	}

	public void excluir(ht_peso ht) {
		this.pDAO = new PesoDAO();
		this.pDAO.excluir(ht);
		this.msgExcluido();
	}

	public void carregar(ht_peso ht) {
		this.peso.setDataAlteracao(ht.getDataAlteracao());
		this.peso.setPeso(ht.getPeso());
		this.peso.setIdCadastro(ht.getIdCadastro());
		this.peso.setId(ht.getId());
	}

	public void limpar() {
		this.peso = new ht_peso();
	}

	public void msgExcluido() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluído!", "Excluído!"));
	}

	public void redirecionar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		NavigationHandler nh = fc.getApplication().getNavigationHandler();
		nh.handleNavigation(fc, null, "/peso/peso.xhtml?faces-redirect=true");
	}
}
