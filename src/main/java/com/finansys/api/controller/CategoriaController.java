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
	public List<CategoriaDto> listar() {
		List<Categoria> categorias = categoriaRepository.findAll();
		return categoriaDtoAssembler.toCollectionDto(categorias);
	}

	@GetMapping("/{categoriaId}")
	public CategoriaDto buscarPeloId(@PathVariable Long categoriaId) {
		Categoria categoria = categoriaService.buscarOuFalhar(categoriaId);
		return categoriaDtoAssembler.toDto(categoria);
	}

	@PostMapping
	public CategoriaDto adicionar(@RequestBody CategoriaDtoInput categoriaDtoInput) {
		Categoria categoria = categoriaDtoDisassembler.toDomainObject(categoriaDtoInput);
		categoria = categoriaService.salvar(categoria);
		return categoriaDtoAssembler.toDto(categoria);
	}

	@PutMapping("/{categoriaId}")
	public CategoriaDto atualizar(@PathVariable Long categoriaId, @RequestBody CategoriaDtoInput categoriaDtoInput) {
			Categoria categoriaAtual = categoriaService.buscarOuFalhar(categoriaId);
			Categoria categoria = categoriaDtoDisassembler.toDomainObject(categoriaDtoInput);
			categoriaAtual = categoriaService.atualizar(categoria, categoriaAtual);
			return categoriaDtoAssembler.toDto(categoriaAtual);
	}

	@DeleteMapping("/{categoriaId}")
	public void deletar(@PathVariable Long categoriaId) {
		categoriaService.excluir(categoriaId);
	}

}
