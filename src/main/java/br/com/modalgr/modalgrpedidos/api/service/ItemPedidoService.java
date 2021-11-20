package br.com.modalgr.modalgrpedidos.api.service;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.modalgr.modalgrpedidos.domain.exception.EntidadeNaoEncontradaException;
import br.com.modalgr.modalgrpedidos.domain.model.ItemPedido;
import br.com.modalgr.modalgrpedidos.domain.model.Pedido;
import br.com.modalgr.modalgrpedidos.domain.model.Referencia;
import br.com.modalgr.modalgrpedidos.domain.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ReferenciaService referenciaService;
	
	public List<ItemPedido> pesquisarPorCodRefAndCodInterno(String codRef, String codInterno, Date inicioEntrega, Date fimEntrega){
		return itemPedidoRepository.findByCodRefAndCodInterno(codRef, codInterno, inicioEntrega, fimEntrega);
	}
	
	public List<ItemPedido> pesquisarPorCodRefAndCodInternoAndPedidoId(String codRef, String codInterno, 
																			Date inicioEntrega, Date fimEntrega, Long pedidoId){
		return itemPedidoRepository.findByCodRefAndCodInternoAndPedidoId(codRef, codInterno, inicioEntrega, fimEntrega, pedidoId);
	}
	
	public ItemPedido pesquisarPorId(Long itemPedidoId) {
		ItemPedido itemPedido = itemPedidoRepository.findById(itemPedidoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Item de pedido com id " + itemPedidoId + " não localizado."));
		return itemPedido;
	}
	
	public ItemPedido adicionar(ItemPedido itemPedido, Long pedidoId) {
		ItemPedido itemPedidoInterno = setItemPedido(itemPedido, pedidoId, OffsetDateTime.now());
		return itemPedidoRepository.save(itemPedidoInterno);
	}
	
	public ItemPedido alterar(ItemPedido itemPedido, Long itemPedidoId) {
		ItemPedido itemPedidoInterno = pesquisarPorId(itemPedidoId);
		itemPedido.setId(itemPedidoInterno.getId());
		ItemPedido item = setItemPedido(itemPedido, itemPedidoInterno.getPedido().getId(), itemPedidoInterno.getDataCadastro());
		return itemPedidoRepository.save(item);
	}
	
	public void apagar (Long itemPedidoId) {
		if(!itemPedidoRepository.existsById(itemPedidoId)) {
			throw new EntidadeNaoEncontradaException("Item de pedido com id " + itemPedidoId + " não localizado.");
		}
		itemPedidoRepository.deleteById(itemPedidoId);
	}
	
	private ItemPedido setItemPedido(ItemPedido itemPedido, Long pedidoId, OffsetDateTime dataCadastro) {
		Pedido pedido = pedidoService.pesquisarPorId(pedidoId);
		Referencia referencia = referenciaService.pesquisarPorId(itemPedido.getReferencia().getId());
		itemPedido.setPedido(pedido);
		itemPedido.setReferencia(referencia);
		itemPedido.setDataCadastro(dataCadastro);
		itemPedido.setValorTotal();

		return itemPedido;
	}

}
