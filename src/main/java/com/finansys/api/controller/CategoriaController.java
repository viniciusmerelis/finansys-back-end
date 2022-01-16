package com.finansys.api.controller;

import java.util.List;

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

import com.finansys.api.assembler.CategoriaDtoAssembler;
import com.finansys.api.assembler.disassembler.CategoriaDtoInputDisassembler;
import com.finansys.api.model.CategoriaDto;
import com.finansys.api.model.input.CategoriaDtoInput;
import com.finansys.domain.model.Categoria;
import com.finansys.domain.repository.CategoriaRepository;
import com.finansys.domain.service.CategoriaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private CategoriaDtoAssembler categoriaDtoAssembler;
	
	@Autowired
	private CategoriaDtoInputDisassembler categoriaDtoDisassembler;

	@GetMapping
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}

	@GetMapping("/{categoriaId}")
	public ResponseEntity<Categoria> buscarPeloId(@PathVariable Long categoriaId) {
		return categoriaRepository.findById(categoriaId)
				.map(categoria -> ResponseEntity.ok(categoria))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Categoria salvar(@RequestBody Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@PutMapping("/{categoriaId}")
	public CategoriaDto atualizar(@PathVariable Long categoriaId, @RequestBody CategoriaDtoInput categoriaDtoInput) {
			Categoria categoriaAtual = categoriaService.buscarOuFalhar(categoriaId);
			Categoria categoria = categoriaDtoDisassembler.toDomainObject(categoriaDtoInput);
			categoriaAtual = categoriaService.atualizar(categoria, categoriaAtual);
			return categoriaDtoAssembler.toDto(categoriaAtual);
	}

	@DeleteMapping("/{categoriaId}")
	public ResponseEntity<Void> deletar(@PathVariable Long categoriaId) {
		if (!categoriaRepository.existsById(categoriaId)) {
			return ResponseEntity.notFound().build();
		}
		categoriaRepository.deleteById(categoriaId);
		return ResponseEntity.noContent().build();
	}

}
