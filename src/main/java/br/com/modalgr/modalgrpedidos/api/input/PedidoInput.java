package br.com.modalgr.modalgrpedidos.api.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.modalgr.modalgrpedidos.domain.model.StatusPedido;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoInput {
	
	@Valid
	@NotNull
	private ClienteIdInput cliente;
	
	@NotBlank
	private String numero;
	
	@NotBlank
	private String formaPagamento;
	
	@NotBlank
	private StatusPedido status;
}
