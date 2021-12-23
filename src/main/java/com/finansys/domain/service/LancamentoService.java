package com.finansys.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finansys.domain.exception.EntidadeNaoEncontradaException;
import com.finansys.domain.model.Categoria;
import com.finansys.domain.model.Lancamento;
import com.finansys.domain.repository.LancamentoRepository;

@Service
public class LancamentoService {
	
	private static final String MSG_ENTIDADE_NAO_ENCONTRADA = "O recurso não pode ser encontrado";

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
	
	public Lancamento buscarOuFalhar(Long lancamentoId) {
		return lancamentoRespository.findById(lancamentoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(MSG_ENTIDADE_NAO_ENCONTRADA)));
	}
}
