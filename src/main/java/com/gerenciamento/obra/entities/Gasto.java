package com.gerenciamento.obra.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import com.gerenciamento.obra.entities.enums.StatusGasto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_gasto")
public class Gasto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	private Double valor;
	private LocalDate data;
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name = "id_obra")
	private Obra obra;
	
	@ManyToOne
	@JoinColumn(name = "id_pagador")
	private Pagador pagador;
	
	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	private Fornecedor fornecedor;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_gasto")
	private TipoGasto tipoGasto;
	
	public Gasto() {
		// TODO Auto-generated constructor stub
	}

	

	public Gasto(Long id, String descricao, Double valor, LocalDate data, StatusGasto statusGasto, Obra obra, Pagador pagador,
			Fornecedor fornecedor, TipoGasto tipoGasto) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.obra = obra;
		this.pagador = pagador;
		this.fornecedor = fornecedor;
		this.tipoGasto = tipoGasto;
		setStatus(statusGasto);
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(StatusGasto statusGasto) {
		if (statusGasto != null) {
			this.status = statusGasto.getCode();
		}
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public Pagador getPagador() {
		return pagador;
	}

	public void setPagador(Pagador pagador) {
		this.pagador = pagador;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public TipoGasto getTipoGasto() {
		return tipoGasto;
	}

	public void setTipoGasto(TipoGasto tipoGasto) {
		this.tipoGasto = tipoGasto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gasto other = (Gasto) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
