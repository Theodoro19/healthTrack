package br.com.ht.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "log_geral")
public class log_geral implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(columnDefinition = "bigint")
	private int id;
	@Column(columnDefinition = "varchar(60)")
	private String feitoPor;
	@Column(columnDefinition = "varchar(30)")
	private String tabela;
	@Column(columnDefinition = "bit")
	private boolean inserir;
	@Column(columnDefinition = "bit")
	private boolean alterar;
	@Column(columnDefinition = "bit")
	private boolean deletar;
	@Column(columnDefinition = "varchar(max)")
	private String informacao;
	@Column(columnDefinition = "datetime")
	private Date data;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFeitoPor() {
		return feitoPor;
	}

	public void setFeitoPor(String feitoPor) {
		this.feitoPor = feitoPor;
	}

	public String getTabela() {
		return tabela;
	}

	public void setTabela(String tabela) {
		this.tabela = tabela;
	}

	public boolean isInserir() {
		return inserir;
	}

	public void setInserir(boolean inserir) {
		this.inserir = inserir;
	}

	public boolean isAlterar() {
		return alterar;
	}

	public void setAlterar(boolean alterar) {
		this.alterar = alterar;
	}

	public boolean isDeletar() {
		return deletar;
	}

	public void setDeletar(boolean deletar) {
		this.deletar = deletar;
	}

	public String getInformacao() {
		return informacao;
	}

	public void setInformacao(String informacao) {
		this.informacao = informacao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (alterar ? 1231 : 1237);
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + (deletar ? 1231 : 1237);
		result = prime * result + ((feitoPor == null) ? 0 : feitoPor.hashCode());
		result = prime * result + id;
		result = prime * result + ((informacao == null) ? 0 : informacao.hashCode());
		result = prime * result + (inserir ? 1231 : 1237);
		result = prime * result + ((tabela == null) ? 0 : tabela.hashCode());
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
		log_geral other = (log_geral) obj;
		if (alterar != other.alterar)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (deletar != other.deletar)
			return false;
		if (feitoPor == null) {
			if (other.feitoPor != null)
				return false;
		} else if (!feitoPor.equals(other.feitoPor))
			return false;
		if (id != other.id)
			return false;
		if (informacao == null) {
			if (other.informacao != null)
				return false;
		} else if (!informacao.equals(other.informacao))
			return false;
		if (inserir != other.inserir)
			return false;
		if (tabela == null) {
			if (other.tabela != null)
				return false;
		} else if (!tabela.equals(other.tabela))
			return false;
		return true;
	}

}
