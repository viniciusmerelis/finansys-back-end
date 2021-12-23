package com.finansys.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finansys.domain.model.Categoria;
import com.finansys.domain.model.Lancamento;
import com.finansys.domain.repository.LancamentoRepository;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRespository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	public Lancamento salvar(Lancamento lancamento) {
		Long categoriaId = lancamento.getCategoria().getId();
		Categoria categoria = categoriaService.buscarOuFalhar(categoriaId);
		lancamento.setCategoria(categoria);
		return lancamentoRespository.save(lancamento);
	}
}
