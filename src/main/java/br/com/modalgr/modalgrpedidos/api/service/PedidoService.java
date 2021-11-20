package br.com.modalgr.modalgrpedidos.api.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.modalgr.modalgrpedidos.domain.exception.EntidadeErrorException;
import br.com.modalgr.modalgrpedidos.domain.exception.EntidadeNaoEncontradaException;
import br.com.modalgr.modalgrpedidos.domain.model.Cliente;
import br.com.modalgr.modalgrpedidos.domain.model.Pedido;
import br.com.modalgr.modalgrpedidos.domain.model.StatusPedido;
import br.com.modalgr.modalgrpedidos.domain.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	public List<Pedido> pesquisarPorDocumentoClienteNumero(String documentoCliente, String nomeCliente, String numero){
		return pedidoRepository.findByDocumentoClienteNomeClienteNumero(documentoCliente, nomeCliente, numero);
	}
	
	public Pedido pesquisarPorId(Long pedidoId) {
		Pedido pedido = pedidoRepository.findById(pedidoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Pedido com id " + pedidoId + " não localizado."));
		
		return pedido;
	}

	public Pedido adicionar(Pedido pedido) {
		Pedido pedidoInterno = setPedido(pedido, OffsetDateTime.now());
		return pedidoRepository.save(pedidoInterno);
	}
	
	public Pedido alterar(Pedido pedido, Long pedidoId) {
		Pedido pedidoInterno = pesquisarPorId(pedidoId);
		pedido.setId(pedidoInterno.getId());
		Pedido ped = setPedido(pedido, pedidoInterno.getDataCadastro());
		return pedidoRepository.save(ped);
	}
	
	public void apagar(Long pedidoId) {
		if(pedidoExiste(pedidoId))
		pedidoRepository.deleteById(pedidoId);
	}
	
	public Pedido finalizarPedido(Long pedidoId) {
		Pedido pedido = pesquisarPorId(pedidoId);
		if(estaFinalizado(pedido))
			throw new EntidadeErrorException("Pedido não pode ser finalizado, pois já foi finalizado em " + 
																					pedido.getDataFinalizacao());	
		else if(estaCancelado(pedido))
			throw new EntidadeErrorException("Pedido não pode ser finalizado, pois já foi cancelado em " + 
																					pedido.getDataCancelamento());
		
		pedido.setStatusPedido(StatusPedido.FINALIZADO);
		pedido.setDataFinalizacao(OffsetDateTime.now());
		pedidoRepository.save(pedido);
		return pedido;
	}
	
	public Pedido cancelarPedido(Long pedidoId) {
		Pedido pedido = pesquisarPorId(pedidoId);
		if(estaCancelado(pedido))
			throw new EntidadeErrorException("Pedido não pode ser cancelado, pois já foi cancelado em " + 
																					pedido.getDataCancelamento());	
		else if(estaFinalizado(pedido))
			throw new EntidadeErrorException("Pedido não pode ser cancelado, pois já foi finalizado em " + 
																					pedido.getDataFinalizacao());
		
		pedido.setStatusPedido(StatusPedido.CANCELADO);
		pedido.setDataCancelamento(OffsetDateTime.now());
		pedidoRepository.save(pedido);
		return pedido;
	}
	
	private Pedido setPedido(Pedido pedido, OffsetDateTime dataCadastro) {
		Cliente cliente = clienteService.pesquisarPorId(pedido.getCliente().getId());
		pedido.setCliente(cliente);
		pedido.setStatusPedido(StatusPedido.ATIVO);
		pedido.setDataCadastro(dataCadastro);
		pedido.setUltimaAlteracao(OffsetDateTime.now());
		
		return pedido;
	}
	
	private boolean pedidoExiste(Long pedidoId) {
		if(!pedidoRepository.existsById(pedidoId)) {
			throw new EntidadeNaoEncontradaException("Pedido com id " + pedidoId + " não localizado.");
		}
		return true;
	}
	
	private boolean estaFinalizado(Pedido pedido) {
		if(pedido.getStatusPedido() == StatusPedido.FINALIZADO)
			return true;
		else return false;
	}
	
	private boolean estaCancelado(Pedido pedido) {
		if(pedido.getStatusPedido() == StatusPedido.CANCELADO)
			return true;
		else return false;
	}
}
