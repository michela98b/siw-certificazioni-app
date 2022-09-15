package it.uniroma3.siw.certificazioni.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.certificazioni.model.Certification;
import it.uniroma3.siw.certificazioni.service.CertificationService;

@Controller
public class CertificationController {

	@Autowired
	CertificationService certificationService;


	@RequestMapping(value="/certifications", method = RequestMethod.GET)
	public String getCertifications(Model model) {
		model.addAttribute("certifications", this.certificationService.findAll());
		return "certifications";
	}

	@RequestMapping(value = "/certification/{id}", method = RequestMethod.GET)
	public String getCertification(@PathVariable("id") Long id, Model model) {
		model.addAttribute("certification", this.certificationService.findById(id));
		return "certification";
	}

	@RequestMapping(value="/admin/certifications", method = RequestMethod.GET)
	public String getAdminCertifications(Model model) {
		model.addAttribute("certifications", this.certificationService.findAll());
		return "admin/certifications";
	}

	@RequestMapping(value="/admin/certification", method = RequestMethod.GET)
	public String geCertificationForm(Model model) {
		model.addAttribute("certification", new Certification());
		return "admin/certificationForm";
	}

	@RequestMapping(value="/admin/addCertification", method = RequestMethod.POST)
	public String addCertification(@Valid @ModelAttribute("certification") Certification certification,
			BindingResult bindingResult, Model model) throws IOException {


		if(!bindingResult.hasErrors()) {

			this.certificationService.save(certification);

			model.addAttribute("certifications", this.certificationService.findAll());
			return "admin/certifications";

		}

		return "admin/certificationForm";
	}



}
