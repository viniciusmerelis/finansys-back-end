package com.finansys.api.model.input;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.stereotype.Component;

import com.finansys.domain.model.TipoLancamento;


@Component
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
	
	@NotNull
	private CategoriaDtoIdInput categoria;
	
}
