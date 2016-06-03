package com.services.persistence.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.services.model.Customer;
import com.services.model.constant.Constants;
import com.services.persistence.CustomerDAO;
import com.services.persistence.DAOUtils;

@Repository("CustomerDAO")
public class CustomerDAOImpl extends DAOUtils implements CustomerDAO{

	@Override
	public void addCustomer(Customer customer) throws Exception {
		this.getMongoOperations().save(customer,Constants.CUSTOMER_COLLECTION);
	}

	public Customer getCustomerByEmail(String email)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));
		
		return this.getMongoOperations().findOne(query, Customer.class,Constants.CUSTOMER_COLLECTION);
	}
	
}
