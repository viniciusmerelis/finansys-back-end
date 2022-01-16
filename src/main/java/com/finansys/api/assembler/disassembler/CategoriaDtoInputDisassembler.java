package com.finansys.api.assembler.disassembler;

import org.springframework.stereotype.Component;

import com.finansys.api.model.input.CategoriaDtoInput;
import com.finansys.domain.model.Categoria;

@Component
public class CategoriaDtoInputDisassembler {

	public Categoria toDomainObject(CategoriaDtoInput categoriaDtoInput) {
		Categoria categoria = new Categoria();
		categoria.setNome(categoriaDtoInput.getNome());
		categoria.setDescricao(categoriaDtoInput.getDescricao());
		return categoria;
	}
}
