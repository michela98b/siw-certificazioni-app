package it.uniroma3.siw.certificazioni.controller;

import java.io.IOException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.certificazioni.model.Exam;
import it.uniroma3.siw.certificazioni.service.CertificationService;
import it.uniroma3.siw.certificazioni.service.ExamService;

@Controller
public class ExamController {

	@Autowired
	ExamService examService;

	@Autowired
	CertificationService certificationService;

	@RequestMapping(value="/admin/exam", method = RequestMethod.GET)
	public String getExamForm(Model model) {
		model.addAttribute("exam", new Exam());
		model.addAttribute("certifications", this.certificationService.findAll());
		return "admin/examForm";
	}

	@RequestMapping(value="/admin/addExam", method = RequestMethod.POST)
	public String addExam(@Valid @ModelAttribute("exam") Exam exam,
			BindingResult bindingResult, Model model) throws IOException {

		if(!bindingResult.hasErrors()) {
			this.examService.save(exam);
			return "admin/home";
		}

		model.addAttribute("certifications", this.certificationService.findAll());
		return "admin/examForm";
	}
}
