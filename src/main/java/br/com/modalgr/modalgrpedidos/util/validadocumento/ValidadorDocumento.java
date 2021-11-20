package br.com.modalgr.modalgrpedidos.util.validadocumento;

public class ValidadorDocumento {

	public boolean validar(Documento documento, String numeroDocumento) {
		
		return documento.validaDocumento(numeroDocumento);
	}
}
