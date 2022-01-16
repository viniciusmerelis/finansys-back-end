package com.finansys.domain.exception;

public class LancamentoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public LancamentoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public LancamentoNaoEncontradoException(Long lancamentoId) {
		this(String.format("Não existe um cadastro de lancamento com código %d\"", lancamentoId));
	}
}
