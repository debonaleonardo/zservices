package com.services.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.services.model.Customer;
import com.services.persistence.CustomerDAO;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private CustomerDAO customerDAO; 
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addCustomer(@RequestBody Customer customer) {
		String returnDescription = "";
		log.debug("Inicio add customer");
		try {
			if(customer != null)
			{
				log.debug("Buscando customer " + customer.getEmail() + " na base");
				Customer customerExists = this.getCustomerDAO().getCustomerByEmail(customer.getEmail());
				if(customerExists == null)
				{
					log.debug("customer " + customer.getEmail() + " nao localizado na base, inserindo");
					this.getCustomerDAO().addCustomer(customer);
					
					returnDescription = "Customer "+customer.getName()+" added sucessfully";
				}
				else
				{
					returnDescription = "The email: " + customer.getEmail() + " is already registered, try other";
				}
					
			}
		} catch (Exception e) {
			log.debug("Errro ao inserir customer -> " + e.toString());
			log.error(e);
		}
		
		return returnDescription;
	}
	
	@RequestMapping(value = "/getcustomer/{email}", method = RequestMethod.GET)
	public Customer getCustomerByEmail(@PathVariable("email") String email) {

		Customer customer = null;
		try {
			if(email != null)
			{
				customer = this.getCustomerDAO().getCustomerByEmail(email);	
			}
		} catch (Exception e) {
			log.debug("Erro ao obter customer");
			log.error(e);
		}
		
		return customer;
	}

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

}
