package br.com.modalgr.modalgrpedidos.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import br.com.modalgr.modalgrpedidos.api.model.ReferenciaModel;
import br.com.modalgr.modalgrpedidos.api.service.ReferenciaService;
import br.com.modalgr.modalgrpedidos.domain.model.Referencia;

@RestController
@RequestMapping("/referencias")
public class ReferenciaController {

	@Autowired
	private ReferenciaService referenciaService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ReferenciaModel> listar(@RequestParam(value="codRef", required=false, defaultValue="%") String codRef,
										@RequestParam(value="codInterno", required=false, defaultValue="%") String codInterno){
		return toCollectionModel(referenciaService.pesquisarPorCodRefCodInterno(codRef, codInterno));
	}
	
	@GetMapping("/{referenciaId}")
	public ResponseEntity<ReferenciaModel> pesquisar(@PathVariable Long referenciaId){
		Referencia referencia =referenciaService.pesquisarPorId(referenciaId);
		return ResponseEntity.ok(toModel(referencia));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ReferenciaModel adicionar(@Valid @RequestBody Referencia referencia) {
		return toModel(referenciaService.adicionar(referencia));
	}
	
	@PutMapping("/{referenciaId}")
	public ResponseEntity<ReferenciaModel> alterar(@Valid @RequestBody Referencia referencia, @PathVariable Long referenciaId){
		Referencia referenciaInterna = referenciaService.alterar(referencia, referenciaId);
		return ResponseEntity.ok(toModel(referenciaInterna));
	}
	
	@DeleteMapping("/{referenciaId}")
	public ResponseEntity<Void> apagar(@PathVariable Long referenciaId){
		referenciaService.apagar(referenciaId);
		return ResponseEntity.noContent().build();
	}
	
	private ReferenciaModel toModel(Referencia referencia) {
		return modelMapper.map(referencia, ReferenciaModel.class);
	}
	
//	private Referencia toEntity(ReferenciaModel referenciaModel) {
//		return modelMapper.map(referenciaModel, Referencia.class);
//	}
	
	private List<ReferenciaModel> toCollectionModel(List<Referencia> listaReferencia){
		return listaReferencia.stream().map(referencia -> toModel(referencia)).collect(Collectors.toList());
	}
}
