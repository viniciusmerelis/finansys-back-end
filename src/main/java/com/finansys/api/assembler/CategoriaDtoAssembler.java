package com.finansys.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.finansys.api.model.CategoriaDto;
import com.finansys.domain.model.Categoria;

@Component
public class CategoriaDtoAssembler {

	public CategoriaDto toDto(Categoria categoria) {
		CategoriaDto categoriaDto = new CategoriaDto();
		categoriaDto.setId(categoria.getId());
		categoriaDto.setNome(categoria.getNome());
		categoriaDto.setDescricao(categoria.getDescricao());
		return categoriaDto;
	}
	
	public List<CategoriaDto> toCollectionDto(List<Categoria> categorias) {
		return categorias.stream()
				.map(categoria -> toDto(categoria))
				.collect(Collectors.toList());
	}
	
}
