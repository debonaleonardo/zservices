package com.services.persistence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.services.model.Customer;
import com.services.persistence.ServicesDAO;

@Repository("ServicesDAO")
public class ServicesDAOImpl implements ServicesDAO{

	@Autowired
	private MongoOperations mongoOperations;
	
	@Override
	public void addCustomer(Customer customer) throws Exception {
		this.getMongoOperations().save(customer);
	}

	public Customer getCustomerByEmail(String email)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));
		
		return this.getMongoOperations().findOne(query, Customer.class);
	}
	
	public MongoOperations getMongoOperations() {
		return mongoOperations;
	}

	public void setMongoOperations(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

}
