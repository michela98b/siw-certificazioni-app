package it.uniroma3.siw.certificazioni.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.certificazioni.model.Enrollment;

public interface EnrollmentRepository extends CrudRepository<Enrollment,Long> {
	

}
