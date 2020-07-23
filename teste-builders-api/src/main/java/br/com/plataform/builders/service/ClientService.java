package br.com.plataform.builders.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.plataform.builders.model.Client;
import br.com.plataform.builders.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public Client add(Client client) {

		Client clientSearch = getByCpf(client.getCpf());

		if (clientSearch == null)
			return clientRepository.save(client);

		return null;
	}

	public List<Client> getAll(Integer page, Integer size) {

		Pageable pageable = PageRequest.of(page, size);

		return clientRepository.findAll(pageable).getContent();
	}

	public Client getById(Long id) {
		return clientRepository.findById(id).orElse(null);
	}

	public Client getByCpf(String cpf) {
		return clientRepository.findByCpf(cpf).orElse(null);
	}

	public Client getByName(String name) {
		return clientRepository.findByName(name).orElse(null);
	}

	public Client update(Long id, Client client) {

		Client clientSearch = getById(id);

		if (clientSearch != null) {
			clientSearch.setAtributes(client);
			return clientRepository.save(clientSearch);
		}

		return null;
	}

	public Client updateName(Long id, String name) {
		Client clientSearch = getById(id);

		if (clientSearch != null) {
			clientSearch.setName(name);
			return clientRepository.save(clientSearch);
		}

		return null;
	}

	public Client delete(Long id) {

		Client clientSearch = getById(id);

		if (clientSearch != null)
			clientRepository.delete(clientSearch);

		return clientSearch;
	}

}
