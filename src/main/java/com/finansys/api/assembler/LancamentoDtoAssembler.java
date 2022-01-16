package com.finansys.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.finansys.api.model.CategoriaDto;
import com.finansys.api.model.LancamentoDto;
import com.finansys.domain.model.Lancamento;

@Component
public class LancamentoDtoAssembler {

	public LancamentoDto toDto(Lancamento lancamento) {
		CategoriaDto categoriaDto = new CategoriaDto();
		categoriaDto.setId(lancamento.getCategoria().getId());
		categoriaDto.setNome(lancamento.getCategoria().getNome());
		categoriaDto.setDescricao(lancamento.getCategoria().getDescricao());
		
		LancamentoDto lancamentoDto = new LancamentoDto();
		lancamentoDto.setId(lancamento.getId());
		lancamentoDto.setNome(lancamento.getNome());
		lancamentoDto.setDescricao(lancamento.getDescricao());
		lancamentoDto.setTipoLancamento(lancamento.getTipoLancamento());
		lancamentoDto.setValor(lancamento.getValor());
		lancamentoDto.setDataLancamento(lancamento.getDataLancamento());
		lancamentoDto.setStatusLancamento(lancamento.getStatusLancamento());
		lancamentoDto.setCategoria(categoriaDto);
		return lancamentoDto;
	}
	
	public List<LancamentoDto> toCollectionDto(List<Lancamento> lancamentos) {
		return lancamentos.stream()
				.map(lancamento -> toDto(lancamento))
				.collect(Collectors.toList());
	}
}
