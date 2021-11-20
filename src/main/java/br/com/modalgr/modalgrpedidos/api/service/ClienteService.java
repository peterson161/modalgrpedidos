package br.com.modalgr.modalgrpedidos.api.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.modalgr.modalgrpedidos.domain.exception.EntidadeErrorException;
import br.com.modalgr.modalgrpedidos.domain.exception.EntidadeNaoEncontradaException;
import br.com.modalgr.modalgrpedidos.domain.model.Cliente;
import br.com.modalgr.modalgrpedidos.domain.repository.ClienteRepository;
import br.com.modalgr.modalgrpedidos.util.validadocumento.Cnpj;
import br.com.modalgr.modalgrpedidos.util.validadocumento.ValidadorDocumento;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> pesquisarPorNomeDocumento(String nome, String documento){
		return clienteRepository.findByNomeDocumento(nome, documento);
	}
	
	public Cliente pesquisarPorId(Long clienteId) {
		Cliente cliente = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente código " + clienteId + " não localizado"));
		
		return cliente;
	}
	
	public Cliente pesquisarPorDocumento(String documento) {
		Cliente cliente = clienteRepository.findByDocumento(documento)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente com documento " + documento + " não localizado."));
		
		return cliente;
	}
	
	public Cliente adicionar(Cliente cliente) {
		if(validaNovoCliente(cliente)) {
			cliente.setDataCadastro(OffsetDateTime.now());
		}
		return clienteRepository.save(cliente);
	}
	
	public Cliente alterar(Cliente cliente, Long clienteId) {
		if(validaCliente(cliente, clienteId)) {
			Cliente clienteInterno = clienteRepository.findById(clienteId).get();
			cliente.setId(clienteInterno.getId());
			cliente.setDataCadastro(clienteInterno.getDataCadastro());
		}
		return clienteRepository.save(cliente);
	}
	
	public void apagar(Long clienteId) {
		Cliente cliente = pesquisarPorId(clienteId);
		clienteRepository.deleteById(cliente.getId());
	}
	
	private boolean validaNovoCliente(Cliente cliente) {
		validaDocumento(cliente.getDocumento());
		documentoNaoExiste(cliente.getDocumento());
		return true;
	}
	
	private boolean validaCliente(Cliente cliente, Long clienteId) {
		validaDocumento(cliente.getDocumento());
		Cliente clienteInterno = pesquisarPorId(clienteId);
		cliente.setId(clienteInterno.getId());
		cliente.setDataCadastro(clienteInterno.getDataCadastro());
		if(clienteInterno.equals(cliente) && documentoExiste(cliente.getDocumento())) {
			return true;
		}
		return false;
	}
	
	private boolean documentoNaoExiste(String numeroDocumento) {
		Optional<Cliente> clienteExiste = clienteRepository.findByDocumento(numeroDocumento);
		if(clienteExiste.isPresent()) {
			throw new EntidadeErrorException("Cliente com documento " + numeroDocumento + " já existe: " + 
																			clienteExiste.get().getNome());			
		}
		return true;
	}
	
	private boolean documentoExiste(String numeroDocumento) {
		pesquisarPorDocumento(numeroDocumento);
		return true;
	}
	
	private boolean validaDocumento(String numeroDocumento) {
		ValidadorDocumento validadorDocumento = new ValidadorDocumento();
		Cnpj cnpj = new Cnpj();
		if (!validadorDocumento.validar(cnpj, numeroDocumento)) {
			throw new EntidadeErrorException("Documento inválido: " + numeroDocumento);
		}
		return true;
	}
}
