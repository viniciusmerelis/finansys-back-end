package com.finansys.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finansys.domain.exception.EntidadeNaoEncontradaException;
import com.finansys.domain.model.Categoria;
import com.finansys.domain.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	private static final String MSG_ENTIDADE_NAO_ENCONTRADA = "O recurso não pode ser encontrado";

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria buscarOuFalhar(Long categoriaId) {
		return categoriaRepository.findById(categoriaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(MSG_ENTIDADE_NAO_ENCONTRADA)));
	}
}
