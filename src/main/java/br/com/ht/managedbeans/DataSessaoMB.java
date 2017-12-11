package br.com.ht.managedbeans;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "dataSessaoMB")
public class DataSessaoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3489185595882365292L;
	private Date dataInicio, dataFim;
	private Integer contaPesquisa;
	private String tipopagto;
	private Integer situacao = 1;

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Integer getContaPesquisa() {
		return contaPesquisa;
	}

	public void setContaPesquisa(Integer contaPesquisa) {
		this.contaPesquisa = contaPesquisa;
	}

	public String getTipopagto() {
		return tipopagto;
	}

	public void setTipopagto(String tipopagto) {
		this.tipopagto = tipopagto;
	}

	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}

}
