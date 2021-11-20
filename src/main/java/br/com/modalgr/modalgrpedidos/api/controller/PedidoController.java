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

import br.com.modalgr.modalgrpedidos.api.model.PedidoListModel;
import br.com.modalgr.modalgrpedidos.api.model.PedidoModel;
import br.com.modalgr.modalgrpedidos.api.service.PedidoService;
import br.com.modalgr.modalgrpedidos.domain.model.Pedido;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<PedidoListModel> listar
						(@RequestParam(value="documentoCliente", required = false, defaultValue="%") String documentoCliente,
						 @RequestParam(value="nomeCliente", required = false, defaultValue="%") String nomeCliente,
						 @RequestParam(value="numero", required = false, defaultValue="%") String numero){
		return toCollectionModel(pedidoService.pesquisarPorDocumentoClienteNumero(documentoCliente, nomeCliente, numero));
	}
	
	@GetMapping("/{pedidoId}")
	public ResponseEntity<PedidoModel> pesquisar(@PathVariable Long pedidoId) {
		Pedido pedido = pedidoService.pesquisarPorId(pedidoId);
		PedidoModel pedidoModel = toModel(pedido);
		return ResponseEntity.ok(pedidoModel);
	}
	
	@PostMapping
	public Pedido adicionar(@Valid @RequestBody Pedido pedido) {
		return pedidoService.adicionar(pedido);
	}
	
	@PutMapping("/{pedidoId}")
	public ResponseEntity<Pedido> alterar(@Valid @RequestBody Pedido pedido, @PathVariable Long pedidoId) {
		Pedido pedidoInterno = pedidoService.alterar(pedido, pedidoId);
		return ResponseEntity.ok(pedidoInterno);
	}
	
	@DeleteMapping("/{pedidoId}")
	public ResponseEntity<Void> apagar(@PathVariable Long pedidoId){
		pedidoService.apagar(pedidoId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{pedidoId}/cancelar")
	public ResponseEntity<PedidoModel> cancelar(@PathVariable Long pedidoId){
		Pedido pedido = pedidoService.cancelarPedido(pedidoId);
		return ResponseEntity.ok(toModel(pedido));
	}
	
	@PutMapping("/{pedidoId}/finalizar")
	public ResponseEntity<PedidoModel> finalizar(@PathVariable Long pedidoId){
		Pedido pedido = pedidoService.cancelarPedido(pedidoId);
		return ResponseEntity.ok(toModel(pedido));
	}
	
	private PedidoModel toModel(Pedido pedido) {
		return modelMapper.map(pedido, PedidoModel.class);
	}
	
	private PedidoListModel toListModel(Pedido pedido) {
		return modelMapper.map(pedido, PedidoListModel.class);
	}
	
	private List<PedidoListModel> toCollectionModel(List<Pedido> listaPedidos){
		return listaPedidos.stream().map(pedido -> toListModel(pedido)).collect(Collectors.toList());
	}
}
