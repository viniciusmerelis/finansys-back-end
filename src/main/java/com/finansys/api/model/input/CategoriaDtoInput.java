package com.finansys.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaDtoInput {

	@NotNull
	private String nome;
	private String descricao;
	
}
