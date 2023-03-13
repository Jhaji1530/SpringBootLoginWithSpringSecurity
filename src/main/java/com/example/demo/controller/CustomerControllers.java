package com.example.demo.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.CommonApiResponseDto;
import com.example.demo.model.Customers;
import com.example.demo.service.CustomerService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customer")
public class CustomerControllers {
	@Autowired
	private CustomerService customerService;

	@PostMapping("/save")
	public CommonApiResponseDto addCustomer(@RequestBody Customers customers) {
		return customerService.addCustomer(customers);
	}

	@GetMapping("/findAll")
	public List<Customers> allCustomers() {

		return customerService.findAllCustomer();
	}

	@GetMapping("/getDetails/{customerId}")
	public Object getCustomerDetails(@PathVariable String customerId) {
		return customerService.getDetailsById(customerId);
	}

	
}
