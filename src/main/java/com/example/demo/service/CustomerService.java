package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.CommonApiResponseDto;
import com.example.demo.model.Customers;
import com.example.demo.repository.CustomersRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomersRepository customerRepository;

	public CommonApiResponseDto addCustomer(Customers customers) {
		// Save all Customers
		System.out.println("Inside Customer service save method!!");
		try {
			if (customers != null) {

				customerRepository.save(customers);
				return new CommonApiResponseDto(1,"Customer Details saved");
			} else {
				return new CommonApiResponseDto(0,"Empty customer detail!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonApiResponseDto(0,"Invalid Operation!!");
		}
	}

	// Find customers
	public List<Customers> findAllCustomer() {
		System.out.println("Inside Find all customers");
		try {
			return customerRepository.findAll();
		} catch (Exception e) {
			return null;
		}
	}

	public Optional<Customers> getDetailsById(String customerId) {
		return customerRepository.findById(customerId);
	}


}
