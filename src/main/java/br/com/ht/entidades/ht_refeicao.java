package br.com.ht.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ht_refeicao")
public class ht_refeicao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(columnDefinition = "numeric(12,2)")
	private double calorias;
	@Column(columnDefinition = "datetime")
	private Date dataInclusao;
	@Column(columnDefinition = "int")
	private int tipoRefeicao;
	@Column(columnDefinition = "int")
	private int idCadastro;
	@Transient
	private String refeicao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCalorias() {
		return calorias;
	}

	public void setCalorias(double calorias) {
		this.calorias = calorias;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(calorias);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result + id;
		result = prime * result + idCadastro;
		result = prime * result + ((refeicao == null) ? 0 : refeicao.hashCode());
		result = prime * result + tipoRefeicao;
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
		ht_refeicao other = (ht_refeicao) obj;
		if (Double.doubleToLongBits(calorias) != Double.doubleToLongBits(other.calorias))
			return false;
		if (dataInclusao == null) {
			if (other.dataInclusao != null)
				return false;
		} else if (!dataInclusao.equals(other.dataInclusao))
			return false;
		if (id != other.id)
			return false;
		if (idCadastro != other.idCadastro)
			return false;
		if (refeicao == null) {
			if (other.refeicao != null)
				return false;
		} else if (!refeicao.equals(other.refeicao))
			return false;
		if (tipoRefeicao != other.tipoRefeicao)
			return false;
		return true;
	}

}
