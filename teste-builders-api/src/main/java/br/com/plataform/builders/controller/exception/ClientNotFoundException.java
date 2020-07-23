package br.com.plataform.builders.controller.exception;

public class ClientNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String path = "api/client";

	private String controller = "ClientController";

	public ClientNotFoundException(Long id) {
		super("Client com ID: " + id + " não encontrado!");
	}

	public ClientNotFoundException() {
		super("Client não encontrado!");
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
