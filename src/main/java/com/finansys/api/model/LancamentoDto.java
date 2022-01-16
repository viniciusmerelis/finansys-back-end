package com.finansys.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.finansys.api.model.input.CategoriaDtoIdInput;
import com.finansys.domain.model.TipoLancamento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LancamentoDto {

	private Long id;
	private String nome;
	private String descricao;
	private TipoLancamento tipoLancamento;
	private BigDecimal valor;
	private LocalDateTime dataLancamento;
	private Boolean statusLancamento;
	private CategoriaDtoIdInput categoria;
	
}
