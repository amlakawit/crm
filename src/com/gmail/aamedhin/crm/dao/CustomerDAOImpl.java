package com.gmail.aamedhin.crm.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gmail.aamedhin.crm.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// inject session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {

		//get current Hibernate session
		Session  session = sessionFactory.getCurrentSession();		
		
		//get a query
		Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);
		
		//execute query and get result list
		List<Customer> customers = query.getResultList();
		
		//return the results		
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		//get current Hibernate session
		Session  session = sessionFactory.getCurrentSession();	
		session.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) {
	
		Session session = sessionFactory.getCurrentSession();
		
		//read customer
		Customer customer = session.get(Customer.class, id);
	
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {

		Session  session = sessionFactory.getCurrentSession();	
		
		// delete object with primary key
		Query query = 
				session.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", id);
		
		query.executeUpdate();			
	}

	@Override
	public List<Customer> searchCustomers(String searchStr) {
		
		// get the current hibernate session
				Session session = sessionFactory.getCurrentSession();
				
				Query theQuery = null;
				
				//
				// only search by name if theSearchName is not empty
				//
				if (searchStr != null && searchStr.trim().length() > 0) {

					// search for firstName or lastName ... case insensitive
					theQuery =session.createQuery("from Customer where lower(firstName) like :searchName or lower(lastName) like :searchName", Customer.class);
					theQuery.setParameter("searchName", "%" + searchStr.toLowerCase() + "%");

				}
				else {
					// theSearchName is empty ... so just get all customers
					theQuery =session.createQuery("from Customer", Customer.class);			
				}
				
				// execute query and get result list
				List<Customer> customers = theQuery.getResultList();
						
				// return the results		
				return customers;
	}




}
