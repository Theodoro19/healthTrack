package br.com.ht.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ht_pressao")
public class ht_pressao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(columnDefinition = "char(3)")
	private String primeiroValor;
	@Column(columnDefinition = "char(3)")
	private String segundoValor;
	@Column(columnDefinition = "datetime")
	private Date dataInclusao;
	@Column(columnDefinition = "int")
	private int idCadastro;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrimeiroValor() {
		return primeiroValor;
	}

	public void setPrimeiroValor(String primeiroValor) {
		this.primeiroValor = primeiroValor;
	}

	public String getSegundoValor() {
		return segundoValor;
	}

	public void setSegundoValor(String segundoValor) {
		this.segundoValor = segundoValor;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public int getIdCadastro() {
		return idCadastro;
	}

	public void setIdCadastro(int idCadastro) {
		this.idCadastro = idCadastro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result + id;
		result = prime * result + idCadastro;
		result = prime * result + ((primeiroValor == null) ? 0 : primeiroValor.hashCode());
		result = prime * result + ((segundoValor == null) ? 0 : segundoValor.hashCode());
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
		ht_pressao other = (ht_pressao) obj;
		if (dataInclusao == null) {
			if (other.dataInclusao != null)
				return false;
		} else if (!dataInclusao.equals(other.dataInclusao))
			return false;
		if (id != other.id)
			return false;
		if (idCadastro != other.idCadastro)
			return false;
		if (primeiroValor == null) {
			if (other.primeiroValor != null)
				return false;
		} else if (!primeiroValor.equals(other.primeiroValor))
			return false;
		if (segundoValor == null) {
			if (other.segundoValor != null)
				return false;
		} else if (!segundoValor.equals(other.segundoValor))
			return false;
		return true;
	}

}
