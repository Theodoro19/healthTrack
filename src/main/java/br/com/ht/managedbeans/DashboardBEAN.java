package br.com.ht.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import br.com.ht.managedbeans.SessaoMB;

@ViewScoped
@ManagedBean(name = "dashboard")
public class DashboardBEAN implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	private DashboardModel model;

	public DashboardModel getModel() {
		return model;
	}

	public void setModel(DashboardModel model) {
		this.model = model;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	@PostConstruct
	public void init() {
		model = new DefaultDashboardModel();
		DashboardColumn coluna1 = new DefaultDashboardColumn();
		DashboardColumn coluna2 = new DefaultDashboardColumn();
		DashboardColumn coluna3 = new DefaultDashboardColumn();
		DashboardColumn coluna4 = new DefaultDashboardColumn();
		coluna1.addWidget("refeicao");
		coluna3.addWidget("peso");
		coluna2.addWidget("atividade");
		coluna4.addWidget("pressao");
		model.addColumn(coluna1);
		model.addColumn(coluna2);
		model.addColumn(coluna3);
		model.addColumn(coluna4);
	}
}
