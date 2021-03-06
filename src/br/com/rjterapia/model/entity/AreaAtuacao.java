package br.com.rjterapia.model.entity;

// Generated 19/02/2015 15:53:21 by Hibernate Tools 4.3.1

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

/**
 * AreaAtuacao generated by hbm2java
 */
@Entity
@Table(name = "area_atuacao")
public class AreaAtuacao implements java.io.Serializable {

	private Integer id;
	private String descricao;
	private String nome;
	private Set<Profissional> profissionals = new HashSet<Profissional>(0);
	private Integer posicao;

	public AreaAtuacao() {
	}

	public AreaAtuacao(String descricao, String nome,
			Set<Profissional> profissionals, Integer posicao) {
		this.descricao = descricao;
		this.nome = nome;
		this.profissionals = profissionals;
		this.posicao = posicao;
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

	@Column(name = "descricao", length = 45)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "nome", length = 45)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "areaAtuacao")
	public Set<Profissional> getProfissionals() {
		return this.profissionals;
	}

	public void setProfissionals(Set<Profissional> profissionals) {
		this.profissionals = profissionals;
	}

	@Column(name = "posicao")
	public Integer getPosicao() {
		return posicao;
	}

	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}

}
