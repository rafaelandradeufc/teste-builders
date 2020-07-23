package br.com.plataform.builders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.plataform.builders.controller.exception.ClientExistsException;
import br.com.plataform.builders.controller.exception.ClientNotFoundException;
import br.com.plataform.builders.model.Client;
import br.com.plataform.builders.service.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientController {

	@Autowired
	private ClientService clientService;

	/* Método POST para adicionar um Client */

	@PostMapping
	public ResponseEntity<Client> add(@RequestBody Client client) {
		Client clientResponse = clientService.add(client);
		if (clientResponse != null)
			return new ResponseEntity<Client>(clientResponse, HttpStatus.CREATED);

		throw new ClientExistsException(client.getCpf());
	}

	/*
	 * Método GET para buscar todos os Clients páginados com ou sem parâmetros de
	 * filtro a partir dos campos Name e Cpf
	 */

	@GetMapping
	public ResponseEntity<?> get(@RequestParam(required = false) String name,
			@RequestParam(required = false) String cpf,
			@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "10") Integer size) {

		Client clientResponse = null;

		if (name != null) {

			clientResponse = clientService.getByName(name);

			if (clientResponse != null)
				return new ResponseEntity<Client>(clientResponse, HttpStatus.OK);

			throw new ClientNotFoundException();

		} else if (cpf != null) {

			clientResponse = clientService.getByCpf(cpf);

			if (clientResponse != null)
				return new ResponseEntity<Client>(clientResponse, HttpStatus.OK);

			throw new ClientNotFoundException();

		} else {

			return new ResponseEntity<List<Client>>(clientService.getAll(page, size), HttpStatus.OK);
		}
	}

	/* Método GET para buscar um Client pelo seu campo Id */

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {

		Client clientResponse = clientService.getById(id);

		if (clientResponse != null)
			return new ResponseEntity<Client>(clientResponse, HttpStatus.OK);

		throw new ClientNotFoundException(id);
	}

	/* Método PUT para atualizar Client por completo */

	@PutMapping("/{id}")
	public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client) {
		Client clientResponse = clientService.update(id, client);

		if (clientResponse != null)
			return new ResponseEntity<Client>(clientResponse, HttpStatus.OK);

		throw new ClientNotFoundException(id);
	}

	/* Método PATCH para atualizar apenas o campo Name de um Client */

	@PatchMapping("/{id}")
	public ResponseEntity<Client> updateName(@PathVariable Long id, @RequestParam String name) {
		Client clientResponse = clientService.updateName(id, name);

		if (clientResponse != null)
			return new ResponseEntity<Client>(clientResponse, HttpStatus.OK);

		throw new ClientNotFoundException(id);
	}

	/* Método DELETE para remover um Client pelo seu campo Id */

	@DeleteMapping("/{id}")
	public ResponseEntity<Client> delete(@PathVariable Long id) {
		Client clientResponse = clientService.delete(id);

		if (clientResponse != null)
			return new ResponseEntity<Client>(clientResponse, HttpStatus.OK);

		throw new ClientNotFoundException(id);
	}

}
