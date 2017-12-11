package br.com.ht.entidades;

import java.io.Serializable;
import java.util.Date;

public class refeicao_join implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7035267946528256711L;
	private int idRefeicao;
	private double calorias;
	private Date dataInclusão;
	private int tipoRefeicao;
	private int idCadastro;
	private String refeicao;

	public int getIdRefeicao() {
		return idRefeicao;
	}

	public void setIdRefeicao(int idRefeicao) {
		this.idRefeicao = idRefeicao;
	}

	public double getCalorias() {
		return calorias;
	}

	public void setCalorias(double calorias) {
		this.calorias = calorias;
	}

	public Date getDataInclusão() {
		return dataInclusão;
	}

	public void setDataInclusão(Date dataInclusão) {
		this.dataInclusão = dataInclusão;
	}

	public int getTipoRefeicao() {
		return tipoRefeicao;
	}

	public void setTipoRefeicao(int tipoRefeicao) {
		this.tipoRefeicao = tipoRefeicao;
	}

	public int getIdCadastro() {
		return idCadastro;
	}

	public void setIdCadastro(int idCadastro) {
		this.idCadastro = idCadastro;
	}

	public String getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(String refeicao) {
		this.refeicao = refeicao;
	}
}
