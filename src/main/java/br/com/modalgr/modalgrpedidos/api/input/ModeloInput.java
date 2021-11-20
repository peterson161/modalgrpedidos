package br.com.modalgr.modalgrpedidos.api.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ModeloInput {
	
	@NotBlank
	@Size(max=1)
	private String genero;
	
	@NotBlank
	@Size(max=40)
	private String nomeModelo;

}
