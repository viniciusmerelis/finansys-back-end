package com.finansys.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.finansys.domain.exception.CategoriaNaoEncontradaException;
import com.finansys.domain.exception.EntidadeEmUsoException;
import com.finansys.domain.model.Categoria;
import com.finansys.domain.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	private static final String MSG_CATEGORIA_EM_USO = "Categoria de código %d não pode ser removida, pois está em uso";

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria atualizar(Categoria categoria, Categoria categoriaAtual) {
		BeanUtils.copyProperties(categoria, categoriaAtual, "id");
		return categoriaRepository.save(categoriaAtual);
	}
	
	public void excluir(Long categoriaId) {
		try {
			categoriaRepository.deleteById(categoriaId);
			categoriaRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new CategoriaNaoEncontradaException(categoriaId); 
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_CATEGORIA_EM_USO, categoriaId));
		}
	}
	
	public Categoria buscarOuFalhar(Long categoriaId) {
		return categoriaRepository.findById(categoriaId)
				.orElseThrow(() -> new CategoriaNaoEncontradaException(categoriaId));
	}
}
