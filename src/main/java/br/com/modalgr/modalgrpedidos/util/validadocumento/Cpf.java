package br.com.modalgr.modalgrpedidos.util.validadocumento;

public class Cpf implements Documento {
	
	@Override
	public boolean validaDocumento(String numeroDocumento) {
		return validaCpf(numeroDocumento);
	}
	
	private boolean validaCpf(String cpf) {
			
		if(cpf.length() != 11) {
			return false;
		}
		
		String cpfInterno = cpf.substring(0, 9);
		int digitoUm = calculaDigitoUm(cpfInterno);
		int digitoDois = CalculaDigitoDois(cpfInterno, digitoUm);
		
		cpfInterno += digitoUm;
		cpfInterno += digitoDois;
		
		if(cpf.equals(cpfInterno)) {
			return true;	
		}
		return false;
	}
	
	private int calculaDigitoUm(String cpf) {
		int SomaCPF = 0;
		for(int i = 0; i < cpf.length();i++) {
			SomaCPF +=  Integer.parseInt(cpf.substring(i, (i+1))) * (i + 1);
		}
		int digito = SomaCPF % 11;
		if(digito == 10) digito = 0;
		
		return digito;
	}

	private int CalculaDigitoDois(String cpf, int digitoUm) {
		int SomaCPF = 0;
		cpf += digitoUm;
		for(int i = 0; i < cpf.length(); i++) {
			SomaCPF += Integer.parseInt(cpf.substring(i,(i+1))) * i;
		}
		int digito = SomaCPF % 11;
		if(digito == 10) digito = 0;
		
		return digito;
	}
}
