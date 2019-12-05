package br.gov.sp.fatec.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import br.gov.sp.fatec.view.View;
import com.fasterxml.jackson.annotation.JsonView;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "lvr_livro")
public class Livro implements Serializable {

	private static final long serialVersionUID = -4175224450033765996L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "lvr_id")
	@JsonView(View.LivroCompleto.class)
	private Long id;

	@Column(name = "lvr_nome", length = 30, nullable = false)
	@JsonView(View.LivroResumo.class)
	private String nome;

	@Column(name = "lvr_descricao", length = 30, nullable = false)
	@JsonView(View.LivroResumo.class)
	private String descricao;

	@Column(name = "lvr_quantidade", nullable = false)
	@JsonView(View.LivroCompleto.class)
	private int quantidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}