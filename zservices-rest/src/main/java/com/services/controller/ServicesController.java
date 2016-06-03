package com.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.services.model.Customer;
import com.services.persistence.ServicesDAO;

@RestController
@RequestMapping("/customers")
public class ServicesController {

	@Autowired
	private ServicesDAO servicesDAO; 
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addCustomer(@RequestBody Customer customer) {

		try {
			if(customer != null)
			{
				this.servicesDAO.addCustomer(customer);	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "1";
	}
	
	@RequestMapping(value = "/getcustomer/{email}", method = RequestMethod.GET)
	public Customer getCustomerByEmail(@PathVariable("email") String email) {

		Customer customer = null;
		try {
			if(email != null)
			{
				customer = this.servicesDAO.getCustomerByEmail(email);	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customer;
	}

	public ServicesDAO getServicesDAO() {
		return servicesDAO;
	}

	public void setServicesDAO(ServicesDAO servicesDAO) {
		this.servicesDAO = servicesDAO;
	}

}
