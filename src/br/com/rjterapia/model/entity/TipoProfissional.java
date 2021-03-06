package br.com.rjterapia.model.entity;

// Generated 19/02/2015 15:53:21 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TipoProfissional generated by hbm2java
 */
@Entity
@Table(name = "tipo_profissional")
public class TipoProfissional implements java.io.Serializable {

	private Integer id;
	private String descricao;
	private Date dtIncl;
	private String nome;
	private Set<Profissional> profissionals = new HashSet<Profissional>(0);

	public TipoProfissional() {
	}

	public TipoProfissional(String descricao, Date dtIncl, String nome) {
		this.descricao = descricao;
		this.dtIncl = dtIncl;
		this.nome = nome;
	}

	public TipoProfissional(String descricao, Date dtIncl, String nome,
			Set<Profissional> profissionals) {
		this.descricao = descricao;
		this.dtIncl = dtIncl;
		this.nome = nome;
		this.profissionals = profissionals;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "descricao", nullable = false, length = 45)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_incl", nullable = false, length = 19)
	public Date getDtIncl() {
		return this.dtIncl;
	}

	public void setDtIncl(Date dtIncl) {
		this.dtIncl = dtIncl;
	}

	@Column(name = "nome", nullable = false, length = 45)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoProfissional")
	public Set<Profissional> getProfissionals() {
		return this.profissionals;
	}

	public void setProfissionals(Set<Profissional> profissionals) {
		this.profissionals = profissionals;
	}

}
