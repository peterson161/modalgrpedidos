package br.com.modalgr.modalgrpedidos.domain.exception;

public class EntidadeErrorException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EntidadeErrorException(String message) {
		super(message);
	}
}
