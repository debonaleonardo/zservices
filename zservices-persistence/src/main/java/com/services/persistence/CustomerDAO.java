package com.services.persistence;

import com.services.model.Customer;

public interface CustomerDAO {

	/**
	 * Adiciona um customer na base
	 * @param Customer customer
	 * @throws Exception
	 * */
	void addCustomer(Customer customer) throws Exception;
	
	/**
	 * Recupera um customer 
	 * pelo email
	 * @param String email
	 * @return Customer localizado (null se nao localizado)
	 * @throws Exception
	 * */
	Customer getCustomerByEmail(String email);
	
}
