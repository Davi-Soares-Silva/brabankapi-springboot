package br.com.senai.davi.brabankapi.services.exceptions;

public class CategoriaNãoEncontradaException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6243168131687113374L;

	public CategoriaNãoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
}
