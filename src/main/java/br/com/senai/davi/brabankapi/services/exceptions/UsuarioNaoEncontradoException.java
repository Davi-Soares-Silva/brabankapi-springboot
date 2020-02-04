package br.com.senai.davi.brabankapi.services.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6243168131687113374L;

	public UsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
}
