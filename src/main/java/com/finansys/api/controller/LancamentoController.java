package com.finansys.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finansys.api.assembler.LancamentoDtoAssembler;
import com.finansys.api.assembler.disassembler.LancamentoDtoInputDisassembler;
import com.finansys.api.model.LancamentoDto;
import com.finansys.api.model.input.LancamentoDtoInput;
import com.finansys.domain.exception.CategoriaNaoEncontradaException;
import com.finansys.domain.exception.NegocioException;
import com.finansys.domain.model.Lancamento;
import com.finansys.domain.repository.LancamentoRepository;
import com.finansys.domain.service.LancamentoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/lancamentos")
public class LancamentoController {

	@Autowired
	private LancamentoRepository lancamentoRespository;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private LancamentoDtoAssembler lancamentoDtoAssembler;
	
	@Autowired
	private LancamentoDtoInputDisassembler lancamentoDtoDisassembler;
	
	@GetMapping
	public List<LancamentoDto> listar() {
		List<Lancamento> lancamentos = lancamentoRespository.findAll();
		return lancamentoDtoAssembler.toCollectionDto(lancamentos);
	}
	
	@GetMapping("/{lancamentoId}")
	public LancamentoDto buscarPeloId(@PathVariable Long lancamentoId) {
		Lancamento lancamento = lancamentoService.buscarOuFalhar(lancamentoId);
		return lancamentoDtoAssembler.toDto(lancamento);
	}
	
	@PostMapping
	public LancamentoDto adicionar(@RequestBody LancamentoDtoInput lancamentoDtoInput) {
		try {
			Lancamento lancamento = lancamentoDtoDisassembler.toDomainObject(lancamentoDtoInput);
			lancamento = lancamentoService.salvar(lancamento);
			return lancamentoDtoAssembler.toDto(lancamento);
		} catch (CategoriaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/{lancamentoId}")
	public LancamentoDto atualizar(@PathVariable Long lancamentoId, @RequestBody LancamentoDtoInput lancamentoDtoInput) {
		try {
			Lancamento lancamentoAtual = lancamentoService.buscarOuFalhar(lancamentoId);
			Lancamento lancamento = lancamentoDtoDisassembler.toDomainObject(lancamentoDtoInput);
			lancamento = lancamentoService.atualizar(lancamento, lancamentoAtual);
			return lancamentoDtoAssembler.toDto(lancamento);
		} catch (CategoriaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@DeleteMapping("/{lancamentoId}")
	public void deletar(@PathVariable Long lancamentoId) {
		lancamentoService.excluir(lancamentoId);
	}
}
