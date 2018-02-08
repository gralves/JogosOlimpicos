package br.com.jogos.olimpicos.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.jogos.olimpicos.type.EtapaType;


@Entity
@Table(name = "competicoes")
public class Competicao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idmodalidade", referencedColumnName = "id")
	private Modalidade modalidade;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idlocal", referencedColumnName = "id")
	private Local local;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pais1_id", referencedColumnName = "id")
	private Pais pais1;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pais2_id", referencedColumnName = "id")
	private Pais pais2;

	@NotNull
	@Enumerated(EnumType.STRING)
	private EtapaType etapa;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "data_inicio")
	private LocalDateTime dataInicio;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "data_termino")
	private LocalDateTime dataTermino;

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDateTime dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	

	public Pais getPais1() {
		return pais1;
	}

	public void setPais1(Pais pais1) {
		this.pais1 = pais1;
	}

	public Pais getPais2() {
		return pais2;
	}

	public void setPais2(Pais pais2) {
		this.pais2 = pais2;
	}

	public EtapaType getEtapa() {
		return etapa;
	}

	public void setEtapa(EtapaType etapa) {
		this.etapa = etapa;
	}
}

