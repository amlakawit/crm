package com.gmail.aamedhin.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gmail.aamedhin.crm.entity.Customer;
import com.gmail.aamedhin.crm.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// inject the customer service
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model model) {

		// get customers from the dao
		List<Customer> customers = customerService.getCustomers();

		// add the customer list to the model
		model.addAttribute("customers", customers);

		return "customer-list";
	}

	@GetMapping("/showFormForAdd")
	public String showFormforAdd(Model model) {

		// create model attribute to bind form data
		Customer customer = new Customer();
		model.addAttribute("customer", customer);

		return "add-customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {

		customerService.saveCustomer(customer);

		return "redirect:/customer/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {

		// get the customer from database
		Customer customer = customerService.getCustomer(id);

		// set customer as model attribute
		model.addAttribute("customer", customer);

		return "update-customer-form";

	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int id){
		
		//delete customer from database
		customerService.deleteCustomer(id);
		
		return "redirect:/customer/list";		
	}
	
	@PostMapping("/search")
	public String searchCustomers(@RequestParam("searchStr") String searchStr, Model model) {

		// search customers from the service
		List<Customer> customers = customerService.searchCustomers(searchStr);
				
		// add the customers to the model
		model.addAttribute("customers", customers);

		return "customer-list";
	}



}
