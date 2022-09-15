package it.uniroma3.siw.certificazioni.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.certificazioni.model.Enrollment;
import it.uniroma3.siw.certificazioni.repository.EnrollmentRepository;

@Service
public class EnrollmentService {
	
	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	@Transactional
	public void save(Enrollment enrollment) {
		this.enrollmentRepository.save(enrollment);
	}
	
	public Enrollment findById(Long id) {
		return this.enrollmentRepository.findById(id).get();
	}

}
