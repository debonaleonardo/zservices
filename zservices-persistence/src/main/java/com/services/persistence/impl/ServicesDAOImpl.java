package com.services.persistence.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.services.model.Service;
import com.services.model.constant.Constants;
import com.services.persistence.DAOUtils;
import com.services.persistence.ServicesDAO;

@Repository("ServicesDAO")
public class ServicesDAOImpl extends DAOUtils implements ServicesDAO{

	@Override
	public void add(Service service) throws Exception {
		this.getMongoOperations().save(service);
	}

	@Override
	public List<Service> listAll() throws Exception {
		return	this.getMongoOperations().findAll(Service.class,Constants.SERVICE_COLLECTION);
	}

	@Override
	public List<Service> listAllByStatus(Integer status) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is(status));
		return this.getMongoOperations().find(query, Service.class,Constants.SERVICE_COLLECTION);
	}

	@Override
	public Service findByName(String name) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		return this.getMongoOperations().findOne(query, Service.class,Constants.SERVICE_COLLECTION);
	}

	

}
