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

import br.com.modalgr.modalgrpedidos.api.service.ClienteService;
import br.com.modalgr.modalgrpedidos.domain.model.Cliente;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Cliente> listar(@RequestParam (value="nome", required = false, defaultValue = "%") String nome,
								@RequestParam (value="documento", required = false, defaultValue = "%") String documento){
		return clienteService.pesquisarPorNomeDocumento(nome, documento);
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente>pesquisarPorId(@PathVariable Long clienteId){
		Cliente cliente = clienteService.pesquisarPorId(clienteId);
		return ResponseEntity.ok(cliente);
	}

	@GetMapping("documento/{numeroDocumento}")
	public ResponseEntity<Cliente>pesquisarPorDocumento(@PathVariable String numeroDocumento){
		Cliente cliente = clienteService.pesquisarPorDocumento(numeroDocumento);
		return ResponseEntity.ok(cliente);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@Valid @RequestBody Cliente cliente){
		return clienteService.adicionar(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> alterar(@Valid @RequestBody Cliente cliente, @PathVariable Long clienteId){
		Cliente clienteInterno = clienteService.alterar(cliente, clienteId);
		return ResponseEntity.ok(clienteInterno);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> apagar(@PathVariable Long clienteId){
		clienteService.apagar(clienteId);
		return ResponseEntity.noContent().build();
	}
}
