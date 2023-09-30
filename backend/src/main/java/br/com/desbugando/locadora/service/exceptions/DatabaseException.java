package br.com.desbugando.locadora.service.exceptions;

public class DatabaseException extends RuntimeException {
	public DatabaseException(String msg) {
		super(msg);
	}
}
