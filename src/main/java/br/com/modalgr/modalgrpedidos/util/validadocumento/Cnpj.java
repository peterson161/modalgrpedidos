package br.com.modalgr.modalgrpedidos.util.validadocumento;

public class Cnpj implements Documento{
	
	@Override
	public boolean validaDocumento(String numeroDocumento) {
		return validaCnpj(numeroDocumento);
	}

	private boolean validaCnpj(String cnpj) {
		if(cnpj.length() != 14) {
			return false;
		}
		
		String cnpjInterno = cnpj.substring(0, 12);
		int digitoUm = calculaDigitoUm(cnpjInterno);
		int digitoDois = calculaDigitoDois(cnpjInterno, digitoUm);
		cnpjInterno += digitoUm;
		cnpjInterno += digitoDois;
		
		if(cnpj.equals(cnpjInterno)){
			return true;
		}
		
		return false;
	}
	
	private int calculaDigitoUm(String cnpj) {
		int multiplicador = 6;
		int somaCnpj = 0;
		int digito = 0;
		for(int i = 0; i < cnpj.length(); i++) {		
			if (multiplicador > 9) multiplicador = 2;
			somaCnpj += Integer.parseInt(cnpj.substring(i, i+1)) * multiplicador;
			multiplicador++;
		}
		digito = somaCnpj % 11;
		if(digito == 10) digito = 0;				
		
		return digito;
	}
	
	private int calculaDigitoDois(String cnpj, int digitoUm) {
		int multiplicador = 5;
		int somaCnpj = 0;
		int digito = 0;
		cnpj += digitoUm;
		for(int i = 0; i < cnpj.length(); i++) {		
			if (multiplicador > 9) multiplicador = 2;
			somaCnpj += Integer.parseInt(cnpj.substring(i, i+1)) * multiplicador;
			multiplicador++;
		}
		digito = somaCnpj % 11;
		if(digito == 10) digito = 0;
		
		return digito;
	}
}
