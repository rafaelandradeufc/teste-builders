package br.com.plataform.builders.model;

public class CustomException {

	private String message;

	private String controller;

	private String path;

	public CustomException(String message, String controller, String path) {
		super();
		this.message = message;
		this.controller = controller;
		this.path = path;
	}

	protected CustomException() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
