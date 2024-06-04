package com.backend.demo.Services;


import com.backend.demo.Dto.PaymentDto;
import com.backend.demo.Entity.Payment;
import com.backend.demo.Entity.Student;
import com.backend.demo.Enums.PaymentStatus;
import com.backend.demo.Enums.PaymentType;
import com.backend.demo.Repository.PaymentRepo;
import com.backend.demo.Repository.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class PaymentService {

	private PaymentRepo paymentRepo;

	private StudentRepo studentRepo;


	@Autowired
	public PaymentService(PaymentRepo paymentRepo , StudentRepo studentRepo) {
		this.paymentRepo = paymentRepo;
		this.studentRepo = studentRepo;
	}

	public Payment savePayment(MultipartFile file ,
	                           PaymentDto newPaymentdto) throws IOException {

		Path path = Paths.get(System.getProperty("user.home"),"students-app-files","payments");

		if (!Files.exists(path)){
			Files.createDirectories(path);
		}
		String fileId = UUID.randomUUID().toString();

		Path filePath = Paths.get(System.getProperty("user.home"),"students-app-files","payments",fileId+".pdf");

		Files.copy(file.getInputStream(),filePath);

		Student student = studentRepo.findByCode(newPaymentdto.getStudentCode());

		Payment payment = Payment.builder()
				.amount(newPaymentdto.getAmount())
				.type(newPaymentdto.getType())
				.date(newPaymentdto.getDate())
				.status(PaymentStatus.CREATED)
				.file(filePath.toUri().toString())
				.student(student)
				.build();

		Payment savedpayment = paymentRepo.save(payment);

		return savedpayment;

	}
}
