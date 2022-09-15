package it.uniroma3.siw.certificazioni.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.certificazioni.model.Exam;

public interface ExamRepository extends CrudRepository<Exam,Long> {

}
