package it.uniroma3.siw.certificazioni.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.certificazioni.model.Certification;

public interface CertificationRepository extends CrudRepository<Certification, Long> {

}
