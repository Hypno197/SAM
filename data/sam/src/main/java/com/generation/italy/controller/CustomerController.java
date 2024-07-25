package com.generation.italy.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.italy.model.Customer;
import com.generation.italy.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return new ResponseEntity<Customer>(customer, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
    	Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<Customer>(createdCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerId, @Valid @RequestBody Customer customerDetails) {
        Customer customer = customerService.getCustomerById(customerId);
        Customer updatedCustomer = customerService.updateCustomer(customerId, customerDetails);
        return ResponseEntity.accepted().body(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable(value = "id") Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok().<Void>build();
    }
}
