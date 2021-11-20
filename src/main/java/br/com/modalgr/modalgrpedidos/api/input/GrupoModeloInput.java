package br.com.modalgr.modalgrpedidos.api.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GrupoModeloInput {
	
	@NotBlank
	@Size(max=50)
	private String nomeGrupo;

}
