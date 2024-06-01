package com.backend.demo;

import com.backend.demo.Entity.Payment;
import com.backend.demo.Entity.Student;
import com.backend.demo.Enums.PaymentType;
import com.backend.demo.Repository.PaymentRepo;
import com.backend.demo.Repository.StudentRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(StudentRepo studentRepo, PaymentRepo paymentRepo){
		return args -> {

			Student student = Student.builder()
					.id(UUID.randomUUID().toString())
					.code("12345")
					.firstName("OMAR")
					.lastName("ABARRA")
					.email("omar@gmail.com")
					.build();

			Student student2 = Student.builder()
					.id(UUID.randomUUID().toString())
					.code("12346")
					.firstName("OMAR")
					.lastName("ABARRA")
					.email("email@gmail.com")
					.build();

			Student student3 = Student.builder()
					.id(UUID.randomUUID().toString())
					.code("12347")
					.firstName("OMAR")
					.lastName("ABARRA")
					.email("email3@gmail.com")
					.build();

			studentRepo.save(student);
			studentRepo.save(student2);
			studentRepo.save(student3);

			PaymentType[] types = PaymentType.values();
			Random random = new Random();

			studentRepo.findAll().forEach(st->{

				int index = random.nextInt(types.length);

				for (int i = 0; i < 10; i++) {
					Payment payment = Payment.builder()
							.amount(1000+(int)(Math.random()*1000))
							.date(java.time.LocalDate.now())
							.type(types[index])
							.file(UUID.randomUUID().toString())
							.student(st)
							.build();

					paymentRepo.save(payment);
				}
			});




		};
	}
}
