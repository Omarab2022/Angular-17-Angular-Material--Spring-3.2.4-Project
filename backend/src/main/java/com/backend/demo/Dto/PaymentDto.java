package com.backend.demo.Dto;

import com.backend.demo.Enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

	private double amount ;

	private PaymentType type ;

	private LocalDate date ;

	private String studentCode;



}
