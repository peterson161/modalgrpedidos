package br.com.modalgr.modalgrpedidos.util.validadocumento;

public enum TipoDocumento {

	CNPJ{
		@Override
		public Documento get() {
			return new Cnpj();
		};
	},
	
	CPF{
		@Override
		public Documento get() {
			return new Cpf();
		}
	};
	
	public abstract Documento get();
}
