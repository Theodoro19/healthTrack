package br.com.ht.entidades;

import java.io.Serializable;
import java.util.Date;

public class atividade_join implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7214851928374099653L;

	private int idAtividade;
	private double calorias;
	private Date dataInclusão;
	private int tipoAtividade;
	private int idCadastro;
	private String atividade;

	public int getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(int idAtividade) {
		this.idAtividade = idAtividade;
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

	public int getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(int tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

	public int getIdCadastro() {
		return idCadastro;
	}

	public void setIdCadastro(int idCadastro) {
		this.idCadastro = idCadastro;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atividade == null) ? 0 : atividade.hashCode());
		long temp;
		temp = Double.doubleToLongBits(calorias);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((dataInclusão == null) ? 0 : dataInclusão.hashCode());
		result = prime * result + idAtividade;
		result = prime * result + idCadastro;
		result = prime * result + tipoAtividade;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		atividade_join other = (atividade_join) obj;
		if (atividade == null) {
			if (other.atividade != null)
				return false;
		} else if (!atividade.equals(other.atividade))
			return false;
		if (Double.doubleToLongBits(calorias) != Double.doubleToLongBits(other.calorias))
			return false;
		if (dataInclusão == null) {
			if (other.dataInclusão != null)
				return false;
		} else if (!dataInclusão.equals(other.dataInclusão))
			return false;
		if (idAtividade != other.idAtividade)
			return false;
		if (idCadastro != other.idCadastro)
			return false;
		if (tipoAtividade != other.tipoAtividade)
			return false;
		return true;
	}

}
