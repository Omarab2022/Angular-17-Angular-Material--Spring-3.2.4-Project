package com.backend.demo.Repository;

import com.backend.demo.Entity.Payment;
import com.backend.demo.Enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepo extends JpaRepository<Payment, Long> {

	List<Payment> findByStudentCode(String code);

	List<Payment> findByStatus(PaymentStatus status);
}
