package br.gov.sp.fatec.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import br.gov.sp.fatec.view.View;
import com.fasterxml.jackson.annotation.JsonView;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "exp_exemplar")
public class Exemplar implements Serializable {

	private static final long serialVersionUID = -4175224450033765996L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "exp_id")
	@JsonView(View.Exemplar.class)
	private Long id;

	@Column(name = "exp_data_aquisicao", nullable = false)
	@JsonView(View.Exemplar.class)
	private Date data_aquisicao;

	@Column(name = "exp_reservado", nullable = false)
	@JsonView(View.Exemplar.class)
	private boolean reservado;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lvr_id")
	@JsonView(View.Exemplar.class)
	private Livro livro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData_aquisicao() {
		return data_aquisicao;
	}

	public void setData_aquisicao(Date data_aquisicao) {
		this.data_aquisicao = data_aquisicao;
	}

	public boolean isReservado() {
		return reservado;
	}

	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
}