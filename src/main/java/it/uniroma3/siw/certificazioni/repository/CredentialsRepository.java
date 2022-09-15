package it.uniroma3.siw.certificazioni.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.certificazioni.model.Credentials;

public interface CredentialsRepository extends CrudRepository<Credentials, Long>{

	public Optional<Credentials> findByUsername(String username);
	public Iterable<Credentials> findByRole(String role);
	
}
