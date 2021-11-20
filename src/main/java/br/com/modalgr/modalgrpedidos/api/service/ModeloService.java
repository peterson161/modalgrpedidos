package br.com.modalgr.modalgrpedidos.api.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.modalgr.modalgrpedidos.domain.exception.EntidadeErrorException;
import br.com.modalgr.modalgrpedidos.domain.exception.EntidadeNaoEncontradaException;
import br.com.modalgr.modalgrpedidos.domain.model.Modelo;
import br.com.modalgr.modalgrpedidos.domain.repository.ModeloRepository;

@Service
public class ModeloService {

	@Autowired
	private ModeloRepository modeloRepository;
	
	public List<Modelo> listar(){
		return modeloRepository.findAll();
	}
	
	public Modelo pesquisarPorId(Long modeloId) {
		return modeloRepository.findById(modeloId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Modelo com id " + modeloId + " não localizado."));
	}
	
	public List<Modelo> pesquisarPorNomeModeloGenero(String nomeModelo, String genero){
		return modeloRepository.findByNomeModeloGenero(nomeModelo, genero);
	}
	
	public Modelo adicionar(Modelo modelo) {
		Modelo modeloInterno = setGenero(modelo);
		modeloInterno.setDataCadastro(OffsetDateTime.now());
		return modeloRepository.save(modeloInterno);
	}
	
	public Modelo alterar(Modelo modelo, Long modeloId) {
		Modelo modeloInterno = pesquisarPorId(modeloId);
		Modelo mod = setGenero(modelo);
		mod.setId(modeloInterno.getId());
		mod.setDataCadastro(modeloInterno.getDataCadastro());
		mod.setUltimaAlteracao(OffsetDateTime.now());
		return modeloRepository.save(mod);
	}
	
	public void apagar(Long modeloId) {
		Modelo modeloInterno = pesquisarPorId(modeloId);
		modeloRepository.deleteById(modeloInterno.getId());
	}
	
	private Modelo setGenero(Modelo modelo) {
		if(modelo.getGenero() == null)
			throw new EntidadeErrorException("Informe um gênero");
		String genero = modelo.getGenero().toUpperCase();
		
		if ((!genero.equals("M")) && (!genero.equals("F"))) {
			throw new EntidadeErrorException("Gênero inválido. Informe M ou F.");
		}
		modelo.setGenero(genero);
		return modelo;
	}
}
