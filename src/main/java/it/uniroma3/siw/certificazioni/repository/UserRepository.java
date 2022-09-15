package it.uniroma3.siw.certificazioni.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.certificazioni.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
