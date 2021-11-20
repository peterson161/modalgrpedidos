package br.com.modalgr.modalgrpedidos.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(Include.NON_NULL)
public class Problema {
	private int status;
	private OffsetDateTime dataHora;
	private String mensagem;
	private List<Campo> campos;
	
	@Setter
	@Getter
	public static class Campo{
		private String campo;
		private String mensagem;
		
		public Campo(String campo, String mensagem) {
			super();
			this.campo = campo;
			this.mensagem = mensagem;
		}
	}
}
