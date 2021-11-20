package br.com.modalgr.modalgrpedidos.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.modalgr.modalgrpedidos.api.service.ModeloService;
import br.com.modalgr.modalgrpedidos.domain.model.Modelo;

@RestController
@RequestMapping("/modelos")
public class ModeloController {
	
	@Autowired
	private ModeloService modeloService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Modelo> listar(@RequestParam(value="nomeModelo", required=false, defaultValue = "%") String nomeModelo,
							  @RequestParam(value="genero", required=false, defaultValue = "%") String genero){
		return modeloService.pesquisarPorNomeModeloGenero(nomeModelo, genero);
	}
	
	@GetMapping("/{modeloId}")
	public ResponseEntity<Modelo> pesquisar(@PathVariable Long modeloId){
		Modelo modelo = modeloService.pesquisarPorId(modeloId);
		return ResponseEntity.ok(modelo);
	}
	
	@PostMapping
	public Modelo adicionar(@Valid @RequestBody Modelo modelo) {
		return modeloService.adicionar(modelo);
	}
	
	@PutMapping("/{modeloId}")
	public ResponseEntity<Modelo> alterar(@Valid @PathVariable Long modeloId, @RequestBody Modelo modelo){
		Modelo modeloInterno = modeloService.alterar(modelo, modeloId);
		return ResponseEntity.ok(modeloInterno);
	}
	
	@DeleteMapping("/{modeloId}")
	public ResponseEntity<Void> apagar(@PathVariable Long modeloId){
		modeloService.apagar(modeloId);
		return ResponseEntity.noContent().build();
	}
}
