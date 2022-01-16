package com.finansys.api.assembler.disassembler;

import org.springframework.stereotype.Component;

import com.finansys.api.model.input.LancamentoDtoInput;
import com.finansys.domain.model.Categoria;
import com.finansys.domain.model.Lancamento;

@Component
public class LancamentoDtoInputDisassembler {

	public Lancamento toDomainObject(LancamentoDtoInput lancamentoDtoInput) {
		Categoria categoria = new Categoria();
		categoria.setId(lancamentoDtoInput.getCategoria().getId());
		
		Lancamento lancamento = new Lancamento();
		lancamento.setNome(lancamentoDtoInput.getNome());
		lancamento.setDescricao(lancamentoDtoInput.getDescricao());
		lancamento.setTipoLancamento(lancamentoDtoInput.getTipoLancamento());
		lancamento.setValor(lancamentoDtoInput.getValor());
		lancamento.setDataLancamento(lancamentoDtoInput.getDataLancamento());
		lancamento.setStatusLancamento(lancamentoDtoInput.getStatusLancamento());
		lancamento.setCategoria(categoria);
		return lancamento;
	}
}
