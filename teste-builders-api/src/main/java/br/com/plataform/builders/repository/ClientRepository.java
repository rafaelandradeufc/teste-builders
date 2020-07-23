package br.com.plataform.builders.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.plataform.builders.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	Optional<Client> findByCpf(String cpf);

	Optional<Client> findByName(String name);


}
