package com.backend.demo.Controllers;


import com.backend.demo.Entity.Payment;
import com.backend.demo.Entity.Student;
import com.backend.demo.Repository.PaymentRepo;
import com.backend.demo.Repository.StudentRepo;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

	private PaymentRepo paymentRepo;

	private StudentRepo studentRepo;

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
 }
