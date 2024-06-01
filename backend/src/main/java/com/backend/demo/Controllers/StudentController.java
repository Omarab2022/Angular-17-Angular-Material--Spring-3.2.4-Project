package com.backend.demo.Controllers;


import com.backend.demo.Entity.Payment;
import com.backend.demo.Entity.Student;
import com.backend.demo.Enums.PaymentStatus;
import com.backend.demo.Enums.PaymentType;
import com.backend.demo.Repository.PaymentRepo;
import com.backend.demo.Repository.StudentRepo;
import com.backend.demo.Services.PaymentService;
import lombok.Getter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class StudentController {

	private PaymentRepo paymentRepo;

	private StudentRepo studentRepo;

	private PaymentService paymentService;

	public StudentController(PaymentRepo paymentRepo , StudentRepo studentRepo) {
		this.paymentRepo = paymentRepo;
		this.studentRepo = studentRepo;
	}

	@GetMapping("/payments")
	public List<Payment> allPayments(){
		return paymentRepo.findAll();
	}

	@GetMapping("/payments/{id}")
	private Payment findById(@PathVariable Long id){
		return paymentRepo.findById(id).get();
	}

	@GetMapping("/students")
	public List<Student> allstudents(){
		return studentRepo.findAll();
	}

	@GetMapping("/students/{code}/payments")
	public List<Payment> findByStudentCode(@PathVariable String code){
		return paymentRepo.findByStudentCode(code);
	}

	@GetMapping("/students/{id}")
	public Student findById(@PathVariable String id){
		return studentRepo.findById(id).get();
	}

	@PostMapping(value = "/payments",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Payment savepayment(@RequestParam MultipartFile file ,
	                           LocalDate date ,
	                           double amount ,
	                           PaymentType type ,
	                           String studentcode) throws IOException {

		return paymentService.savePayment(file,date,amount,type,studentcode);
	}

	@GetMapping(value = "/paymentFile/{paymentid}", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] getPaymentFile(@PathVariable Long paymentid) throws IOException {
		Payment payment = paymentRepo.findById(paymentid).get();
		String filePath = payment.getFile();
		return Files.readAllBytes(Path.of(URI.create(filePath)));
	}

	@PutMapping("/payments/updatestatus/{paymentid}")
	public Payment updatepaymentstatue(@RequestParam PaymentStatus status , @PathVariable Long paymentid){

        Payment payment = paymentRepo.findById(paymentid).get();
		payment.setStatus(status);
		return paymentRepo.save(payment);
	}

 }
