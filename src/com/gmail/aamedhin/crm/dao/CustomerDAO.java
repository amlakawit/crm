package com.gmail.aamedhin.crm.dao;

import java.util.List;

import com.gmail.aamedhin.crm.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);
	
	public Customer getCustomer(int id);

	public void deleteCustomer(int id);

	public List<Customer> searchCustomers(String searchStr);

}
