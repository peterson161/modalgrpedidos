package br.com.modalgr.modalgrpedidos.api.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.modalgr.modalgrpedidos.domain.exception.EntidadeNaoEncontradaException;
import br.com.modalgr.modalgrpedidos.domain.model.Cliente;
import br.com.modalgr.modalgrpedidos.domain.model.GrupoModelo;
import br.com.modalgr.modalgrpedidos.domain.model.Modelo;
import br.com.modalgr.modalgrpedidos.domain.model.Referencia;
import br.com.modalgr.modalgrpedidos.domain.repository.ReferenciaRepository;

@Service
public class ReferenciaService {
	
	@Autowired
	private ReferenciaRepository referenciaRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private GrupoModeloService grupoModeloService;
	
	@Autowired
	private ModeloService modeloService;
	
	public List<Referencia> pesquisarPorCodRefCodInterno(String codRef, String codInterno){
		return referenciaRepository.findByCodRefCodInterno(codRef, codInterno);
	}
	
	public Referencia pesquisarPorId(Long referenciaId) {
		Referencia referencia = referenciaRepository.findById(referenciaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Referencia de id " + referenciaId + " não localizada."));
		return referencia;
	}
	
	public Referencia adicionar(Referencia referencia) {
		return referenciaRepository.save(setReferencia(referencia, OffsetDateTime.now()));
	}
	
	public Referencia alterar(Referencia referencia, Long referenciaId) {
		Referencia referenciaInterna = referenciaRepository.findById(referenciaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Referencia de id " + referenciaId + " não localizada."));
		referencia.setId(referenciaInterna.getId());
		Referencia ref = setReferencia(referencia, referenciaInterna.getDataCadastro());
		referenciaRepository.save(ref);
		return ref;
	}
	
	public void apagar(Long referenciaId) {
		if(!referenciaRepository.existsById(referenciaId)) {
			throw new EntidadeNaoEncontradaException("Referencia de id " + referenciaId + " não localizada.");
		}
		
		referenciaRepository.deleteById(referenciaId);
	}
	
	private Referencia setReferencia(Referencia referencia, OffsetDateTime dataCadastro) {
		Long clienteId = referencia.getCliente().getId();
		Long grupoModeloId = referencia.getGrupoModelo().getId();
		Long modeloId = referencia.getModelo().getId();
		Cliente cliente = clienteService.pesquisarPorId(clienteId);
		GrupoModelo grupoModelo = grupoModeloService.pesquisar(grupoModeloId);	
		Modelo modelo = modeloService.pesquisarPorId(modeloId);
		referencia.setCliente(cliente);
		referencia.setGrupoModelo(grupoModelo);
		referencia.setModelo(modelo);
		referencia.setDataCadastro(dataCadastro);
		
		return referencia;
	}

}
