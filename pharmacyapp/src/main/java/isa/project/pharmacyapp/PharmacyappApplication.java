package isa.project.pharmacyapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableAsync
public class PharmacyappApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmacyappApplication.class, args);
	}



}

@RestController
@RequestMapping("/")
class HelloController{

	@GetMapping
	public String helloWorld(){
		return "<br/><h2>Hello world</h2>" +
				"<h1>Pharmacy App</h1>";
	}

}



