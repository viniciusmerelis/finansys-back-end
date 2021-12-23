package com.finansys.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finansys.domain.exception.EntidadeNaoEncontradaException;
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
	
	@GetMapping
	public List<Lancamento> listar() {
		return lancamentoRespository.findAll();
	}
	
	@GetMapping("/{lancamentoId}")
	public ResponseEntity<Lancamento> buscarPeloId(@PathVariable Long lancamentoId) {
		return lancamentoRespository.findById(lancamentoId)
				.map(lancamento -> ResponseEntity.ok(lancamento))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Lancamento salvar(@RequestBody Lancamento lancamento) {
		return lancamentoService.salvar(lancamento);
	}
	
	@PutMapping("/{lancamentoId}")
	public Lancamento atualizar(@PathVariable Long lancamentoId, @RequestBody Lancamento lancamento) {
		try {
			Lancamento lancamentoAtual = lancamentoService.buscarOuFalhar(lancamentoId);
			BeanUtils.copyProperties(lancamento, lancamentoAtual, "id");
			return lancamentoService.salvar(lancamentoAtual);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@DeleteMapping("/{lancamentoId}")
	public ResponseEntity<Void> deletar(@PathVariable Long lancamentoId) {
		if (!lancamentoRespository.existsById(lancamentoId)) {
			return ResponseEntity.notFound().build();
		}
		lancamentoRespository.deleteById(lancamentoId);
		return ResponseEntity.noContent().build();
	}
}
