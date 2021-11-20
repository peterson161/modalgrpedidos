package br.com.modalgr.modalgrpedidos.api.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.modalgr.modalgrpedidos.domain.exception.EntidadeNaoEncontradaException;
import br.com.modalgr.modalgrpedidos.domain.model.GrupoModelo;
import br.com.modalgr.modalgrpedidos.domain.repository.GrupoModeloRepository;

@Service
public class GrupoModeloService {
	
	@Autowired
	private GrupoModeloRepository grupoModeloRepository;
	
	public List<GrupoModelo> PesquisarPorNomeGrupo(String nomeGrupo){
		return grupoModeloRepository.findByNomeGrupoContaining(nomeGrupo);
	}
	
	public GrupoModelo pesquisar(Long grupoModeloId) {
		GrupoModelo grupoModeloInterno = grupoModeloRepository.findById(grupoModeloId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Grupo de modelo id " + grupoModeloId + " não localizado."));
		return grupoModeloInterno;
	}
	
	public GrupoModelo adicionar(GrupoModelo grupoModelo) {
		grupoModelo.setDataCadastro(OffsetDateTime.now());
		return grupoModeloRepository.save(grupoModelo);
	}

	public GrupoModelo alterar(GrupoModelo grupoModelo, Long grupoModeloId) {
		GrupoModelo grupoModeloInterno = grupoModeloRepository.findById(grupoModeloId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Grupo de modelo id " + grupoModeloId + " não localizado."));
		grupoModelo.setId(grupoModeloInterno.getId());
		grupoModelo.setDataCadastro(grupoModeloInterno.getDataCadastro());
		grupoModelo.setUltimaAlteracao(OffsetDateTime.now());
		return grupoModeloRepository.save(grupoModelo);
	}
	
	public void apagar(Long grupoModeloId) {
		if(!grupoModeloRepository.existsById(grupoModeloId)) {
			throw new EntidadeNaoEncontradaException("Grupo de modelo id " + grupoModeloId + " não localizado.");
		}
		grupoModeloRepository.deleteById(grupoModeloId);
	}
}
