package br.com.modalgr.modalgrpedidos.api.controller;

import java.sql.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.modalgr.modalgrpedidos.api.model.ItemPedidoSoloModel;
import br.com.modalgr.modalgrpedidos.api.service.ItemPedidoService;
import br.com.modalgr.modalgrpedidos.api.service.PedidoService;
import br.com.modalgr.modalgrpedidos.domain.model.ItemPedido;
import br.com.modalgr.modalgrpedidos.domain.model.Pedido;

@RestController
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoService itemPedidoService;
	
	@Autowired
	private PedidoService pedidoService;
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/itens/{itemPedidoId}")
	public ResponseEntity<ItemPedidoSoloModel> pesquisarPorId(@PathVariable Long itemPedidoId){
		ItemPedido itemPedido = itemPedidoService.pesquisarPorId(itemPedidoId);
		return ResponseEntity.ok(toSoloModel(itemPedido));
	}
	
	@GetMapping("/itens")
	@ResponseStatus(HttpStatus.OK)
	public List<ItemPedidoSoloModel> listar(@RequestParam(value="codRef", required = false, defaultValue="%") String codRef,
											@RequestParam(value="codInterno", required = false, defaultValue="%") String codInterno,
							@RequestParam(value="inicioEntrega", required = false, defaultValue="2000-01-01") Date inicioEntrega,
							@RequestParam(value="fimEntrega", required = false, defaultValue="9999-12-31") Date fimEntrega){
		return toCollectionSoloModel(itemPedidoService.pesquisarPorCodRefAndCodInterno(codRef, codInterno, inicioEntrega, fimEntrega));	
}
	
	@PutMapping("/itens/{itemPedidoId}")
	public ResponseEntity<ItemPedidoSoloModel> alterar(@Valid @RequestBody ItemPedido itemPedido, @PathVariable Long itemPedidoId) {
		itemPedidoService.alterar(itemPedido, itemPedidoId);
		return ResponseEntity.ok(toSoloModel(itemPedido));		
	}
	
	@DeleteMapping("/itens/{itemPedidoId}")
	public ResponseEntity<Void> apagar(@PathVariable Long itemPedidoId){
		itemPedidoService.apagar(itemPedidoId);
		return ResponseEntity.noContent().build();
	}
	
	
	// **** Devem ser executados pelo pedido ***
	
	@PostMapping("/pedidos/{pedidoId}/itens")
	@ResponseStatus(HttpStatus.CREATED)
	public ItemPedidoSoloModel adicionar(@Valid @RequestBody ItemPedido itemPedido, @PathVariable Long pedidoId) {
		itemPedidoService.adicionar(itemPedido, pedidoId);
		ItemPedidoSoloModel itemSolo = toSoloModel(itemPedido);
		return itemSolo;
	}
	
	@GetMapping("/pedidos/{pedidoId}/itens")
	@ResponseStatus(HttpStatus.OK)
	public List<ItemPedidoSoloModel> listarItens(@PathVariable Long pedidoId,
									@RequestParam(value="codRef", required = false, defaultValue="%") String codRef,
									@RequestParam(value="codInterno", required = false, defaultValue="%") String codInterno,
								@RequestParam(value="inicioEntrega", required = false, defaultValue="2000-01-01") Date inicioEntrega,
								@RequestParam(value="fimEntrega", required = false, defaultValue="9999-12-31") Date fimEntrega){
		Pedido pedido = pedidoService.pesquisarPorId(pedidoId);
		return toCollectionSoloModel(itemPedidoService.pesquisarPorCodRefAndCodInternoAndPedidoId
																(codRef, codInterno, inicioEntrega, fimEntrega, pedido.getId()));	
	}
	
	// **** Métodos auxiliares ***
	
	public ItemPedidoSoloModel toSoloModel(ItemPedido itemPedido) {
		return modelMapper.map(itemPedido, ItemPedidoSoloModel.class);
	}
	
	public List<ItemPedidoSoloModel> toCollectionSoloModel(List<ItemPedido> listaItens){
		return listaItens.stream().map(itemPedido -> toSoloModel(itemPedido)).collect(Collectors.toList());
	}

}
