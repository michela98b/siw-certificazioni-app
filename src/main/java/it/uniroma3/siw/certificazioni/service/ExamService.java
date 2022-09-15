package it.uniroma3.siw.certificazioni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.certificazioni.model.Exam;
import it.uniroma3.siw.certificazioni.repository.ExamRepository;

@Service
public class ExamService {
	
	@Autowired
	ExamRepository examRepository;
	
	public Exam findById(Long id) {
		return this.examRepository.findById(id).get();
	}
	
	public void save(Exam exam) {
		this.examRepository.save(exam);
	}
	
}
