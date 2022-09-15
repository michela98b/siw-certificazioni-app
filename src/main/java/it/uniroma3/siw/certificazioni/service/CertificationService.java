package it.uniroma3.siw.certificazioni.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.certificazioni.model.Certification;
import it.uniroma3.siw.certificazioni.repository.CertificationRepository;

@Service
public class CertificationService {
	
	@Autowired
	CertificationRepository certificationRepository;
	
	
	public Certification findById(Long id) {
		return this.certificationRepository.findById(id).get();
	}
	
	public List<Certification> findAll(){
		List<Certification> certifications = new ArrayList<>();
		
		for(Certification c: this.certificationRepository.findAll()) {
			certifications.add(c);
		}
		return certifications;
	}
	
	@Transactional
	public void save(Certification certification) {
		this.certificationRepository.save(certification);
	}

}
