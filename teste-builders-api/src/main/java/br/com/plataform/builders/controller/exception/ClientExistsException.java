package br.com.plataform.builders.controller.exception;

public class ClientExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String path = "api/client";

	private String controller = "ClientController";

	public ClientExistsException(String cpf) {
		super("Client de CPF: " + cpf + " já existente!");
	}

	public ClientExistsException() {
		super("Client já existente!");
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

}
