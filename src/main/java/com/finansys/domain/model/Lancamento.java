package com.finansys.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Lancamento {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column
	private String descricao;
	
	@Enumerated(value = EnumType.ORDINAL)
	@Column(nullable = false)
	private TipoLancamento tipoLancamento;
	
	@Column(nullable = false)
	private BigDecimal valor;
	
	@Column(nullable = false)
	private LocalDateTime dataLancamento;
	
	@Column(nullable = false)
	private Boolean statusLancamento;
	
	@ManyToOne
	@JoinColumn(name = "categoriaId", nullable = false)
	private Categoria categoria;

}
