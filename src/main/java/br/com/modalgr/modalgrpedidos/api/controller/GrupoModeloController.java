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

import br.com.modalgr.modalgrpedidos.api.service.GrupoModeloService;
import br.com.modalgr.modalgrpedidos.domain.model.GrupoModelo;

@RestController
@RequestMapping("/grupos-modelo")
public class GrupoModeloController {

	@Autowired
	private GrupoModeloService grupoModeloService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<GrupoModelo> listar(@RequestParam(value="nomeGrupo", required=false, defaultValue="%") String nomeGrupo){
		return grupoModeloService.PesquisarPorNomeGrupo(nomeGrupo);
	}
	
	@GetMapping("/{grupoModeloId}")
	public ResponseEntity<GrupoModelo> pesquisar(@PathVariable Long grupoModeloId){
		GrupoModelo grupoModelo = grupoModeloService.pesquisar(grupoModeloId);
		return ResponseEntity.ok(grupoModelo);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModelo adicionar(@Valid @RequestBody GrupoModelo grupoModelo) {
		return grupoModeloService.adicionar(grupoModelo);
	}
	
	@PutMapping("/{grupoPedidoId}")
	public ResponseEntity<GrupoModelo> alterar(@Valid @RequestBody GrupoModelo grupoModelo, @PathVariable Long grupoPedidoId){
		GrupoModelo grupoModeloInterno = grupoModeloService.alterar(grupoModelo, grupoPedidoId);
		return ResponseEntity.ok(grupoModeloInterno);
	}
	
	@DeleteMapping("/{grupoModeloId}")
	public ResponseEntity<Void> apagar(@PathVariable Long grupoModeloId){
		grupoModeloService.apagar(grupoModeloId);
		return ResponseEntity.noContent().build();
	}
}
