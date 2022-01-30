package com.finansys.api.model.input;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.finansys.domain.model.TipoLancamento;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LancamentoDtoInput {

	@NotNull
	private String nome;
	
	private String descricao;
	
	@NotNull
	private TipoLancamento tipoLancamento;
	
	@NotNull
	@PositiveOrZero
	private BigDecimal valor;
	
	@NotNull
	private LocalDateTime dataLancamento;
	
	@NotNull
	private Boolean statusLancamento;
	
	@Valid
	@NotNull
	private CategoriaDtoIdInput categoria;
	
}
