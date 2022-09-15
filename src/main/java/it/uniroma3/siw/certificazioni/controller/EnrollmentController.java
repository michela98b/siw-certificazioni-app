package it.uniroma3.siw.certificazioni.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.certificazioni.model.Enrollment;
import it.uniroma3.siw.certificazioni.model.User;
import it.uniroma3.siw.certificazioni.service.CredentialsService;
import it.uniroma3.siw.certificazioni.service.EnrollmentService;
import it.uniroma3.siw.certificazioni.service.ExamService;

@Controller
public class EnrollmentController {

	@Autowired
	ExamService examService;

	@Autowired
	CredentialsService credentialsService;

	@Autowired
	EnrollmentService enrollmentService;

	@RequestMapping(value = "/enrollment/{id}", method = RequestMethod.GET)
	public String getExam(@PathVariable("id") Long id, Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = "";

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}

		User user = this.credentialsService.getCredentials(username).getUser();

		Enrollment enrollment = new Enrollment();
		enrollment.setExam(this.examService.findById(id));
		enrollment.setUser(user);

		model.addAttribute("enrollment", enrollment);

		return "enrollmentForm";
	}

	@RequestMapping(value="/enrollment/save", method = RequestMethod.POST)
	public String addEnrollment(@Valid @ModelAttribute("enrollment") Enrollment enrollment, BindingResult bindingResult, Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = "";

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}

		User user = this.credentialsService.getCredentials(username).getUser();

		enrollment.setUser(user);

		if(!bindingResult.hasErrors()) {
			this.enrollmentService.save(enrollment);

			return "home";
		} else {
			return "enrollmentForm";
		}
	}

	@RequestMapping(value = "/enrollments", method = RequestMethod.GET)
	public String getEnrollments(Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = "";

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}

		User user = this.credentialsService.getCredentials(username).getUser();


		model.addAttribute("enrollments", user.getEnrollments());
		model.addAttribute("localDate", LocalDate.now());

		return "enrollments";
	}

	@RequestMapping(value="/enrollment/confirm/{id}", method = RequestMethod.GET)
	public String updateEnrollmentConfirmation(@PathVariable("id") Long id, Model model) {
		model.addAttribute("enrollment", this.enrollmentService.findById(id));
		return "updateEnrollment";
	}

	@RequestMapping(value="/enrollment/update/{id}", method = RequestMethod.GET)
	public String updateEnrollment(@PathVariable("id") Long id, Model model) {
		Enrollment enrollmentFromDb = this.enrollmentService.findById(id);

		enrollmentFromDb.setActive(false);
		this.enrollmentService.save(enrollmentFromDb);
		
		return "home";
	}

}