package it.uniroma3.siw;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import it.uniroma3.siw.certificazioni.model.Certification;
import it.uniroma3.siw.certificazioni.model.Credentials;
import it.uniroma3.siw.certificazioni.model.Exam;
import it.uniroma3.siw.certificazioni.model.User;

@SpringBootApplication
public class SiwCertificazioniApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiwCertificazioniApplication.class, args);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("certificazioni-unit");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		
		User admin = new User();
		admin.setNome("Segreteria");
		admin.setCognome("Uno");

		User studente1 = new User();
		studente1.setNome("Mario");
		studente1.setCognome("Rossi");
		
		Credentials credentials = new Credentials();
		credentials.setUsername("mariorossi@test.it");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		credentials.setPassword(passwordEncoder.encode("test"));
		credentials.setUser(studente1);
		credentials.setRole(Credentials.DEFAULT_ROLE);

		Credentials adminCredentials = new Credentials();
		adminCredentials.setUsername("segreteriauno@test.it");
		adminCredentials.setPassword(passwordEncoder.encode("test"));
		adminCredentials.setUser(admin);
		adminCredentials.setRole(Credentials.ADMIN_ROLE);
		
		Certification certification = new Certification();
		certification.setName("B2 First");
		certification.setDescription("Il B2 First è un certificato di conoscenza generale della lingua inglese rilasciato da Cambridge Assessment English a persone non madrelingua inglese nel caso di superamento dell'esame omonimo.");
		certification.setLevel("Medio-alto");
		certification.setPrice((Integer)150);
		
		Certification certification2 = new Certification();
		certification2.setName("B1 Business Preliminary");
		certification2.setDescription("Il BEC, acronimo di Business English Certificate (Certificazione di Inglese Commerciale), è un certificato di conoscenza specialistica della lingua inglese rilasciato dall'Università di Cambridge");
		certification2.setLevel("Base");
		certification2.setPrice((Integer)125);
		
		Certification certification3 = new Certification();
		certification3.setName("B2 Business Vantage");
		certification3.setDescription("Il BEC, acronimo di Business English Certificate (Certificazione di Inglese Commerciale), è un certificato di conoscenza specialistica della lingua inglese rilasciato dall'Università di Cambridge.");
		certification3.setLevel("Intermedio");
		certification3.setPrice((Integer)180);
		
		Certification certification4 = new Certification();
		certification4.setName("C1 Business Higher");
		certification4.setDescription("Il BEC, acronimo di Business English Certificate (Certificazione di Inglese Commerciale), è un certificato di conoscenza specialistica della lingua inglese rilasciato dall'Università di Cambridge");
		certification4.setLevel("Avanzato");
		certification4.setPrice((Integer)240);
		
		Exam exam = new Exam();
		exam.setDate(LocalDate.of(2022, 10, 5));
		exam.setClassroom("11");
		exam.setCertification(certification);
		
		Exam exam2 = new Exam();
		exam2.setDate(LocalDate.of(2022, 11, 10));
		exam2.setClassroom("2B");
		exam2.setCertification(certification);
		
		tx.begin ();
		em.persist (studente1);
		em.persist(admin);
		em.persist (credentials);
		em.persist(adminCredentials);
		em.persist(certification);
		em.persist(certification2);
		em.persist(certification3);
		em.persist(certification4);
		em.persist(exam);
		em.persist(exam2);
		
		tx.commit ();

		em.close();
		emf.close();
	}

}
