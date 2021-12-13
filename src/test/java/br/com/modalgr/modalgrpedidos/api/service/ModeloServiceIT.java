package br.com.modalgr.modalgrpedidos.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.modalgr.modalgrpedidos.domain.exception.EntidadeErrorException;
import br.com.modalgr.modalgrpedidos.domain.model.Modelo;

@SpringBootTest
public class ModeloServiceIT {
	
	@Autowired
	private ModeloService modeloService;
	
	@Test
	public void shouldCreateNewModelo_WhenNovoModeloGiven() {
		Modelo modelo = new Modelo();
		modelo.setGenero("M");
		modelo.setNomeModelo("BLUSA MOLETOM TESTE");
		
		Modelo modeloSalvo = modeloService.adicionar(modelo);
		
		assertThat(modeloSalvo).isNotNull();
		assertThat(modeloSalvo.getId()).isNotNull();
	}
	
	@Test
	public void shouldCallConstraintViolationException_WhenModeloIsEmptyOrNull() {
		Modelo modelo = new Modelo();
		modelo.setGenero("M");
		Assertions.assertThrows(ConstraintViolationException.class, () -> modeloService.adicionar(modelo));
	}
	
	@Test
	public void shouldCallEntidadeErrorException_WhenGeneroIsNull() {
		Modelo modelo = new Modelo();
		modelo.setNomeModelo("BLUSA MOLETOM TESTE");
		Assertions.assertThrows(EntidadeErrorException.class, () -> modeloService.adicionar(modelo));
	}
	
	@Test
	public void shouldCallEntidadeErrorException_WhenGeneroIsNotEquals_M_or_F(){
		Modelo modelo = new Modelo();
		modelo.setGenero("S");
		modelo.setNomeModelo("BLUSA MOLETOM TESTE");
		Assertions.assertThrows(EntidadeErrorException.class, () -> modeloService.adicionar(modelo));
	}
}
