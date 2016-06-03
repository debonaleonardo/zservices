package com.services.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.services.model.Service;
import com.services.persistence.ServicesDAO;

@RestController
@RequestMapping("/services")
public class ServicesController {

	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private ServicesDAO servicesDAO;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addService(@RequestBody Service service) {
		String returnDescription = "";
		log.debug("Inicio add service");
		try {
			Service serviceExists = this.getServicesDAO().findByName(service.getName());
			
			if(serviceExists == null)
			{
				log.debug("Cadastrando service " + service.getName());
				this.getServicesDAO().add(service);
				returnDescription = "Service " + service.getName() +" registered!";
			}
			else
			{
				returnDescription = "Service " + service.getName() +" already registered!";
			}
		
		} catch (Exception e) {
			log.debug("Errro ao inserir service -> " + e.toString());
			log.error(e);
		}
		
		return returnDescription;
	}
	
	@RequestMapping(value = "/listAll/{status}", method = RequestMethod.GET)
	public List<Service> listAllActiveServices(@PathVariable("status") String status) {
		List<Service> servicesList = null;
		try {
			log.debug("inicio busca dos services");
			
			if(status != null)
			{
				servicesList = this.getServicesDAO().listAllByStatus(Integer.parseInt(status));
				
				if(servicesList != null && !servicesList.isEmpty())
				{
					log.debug("retornando " + servicesList.size() + " servicos cadastrados");
					return servicesList;
				}
				else
				{
					log.debug("Nao foram localizados servicos cadastrados!");
				}
			}
			
		} catch (Exception e) {
			log.debug("Erro ao obter lista de services");
			log.error(e);
		}
		return null;
	}

	public ServicesDAO getServicesDAO() {
		return servicesDAO;
	}

	public void setServicesDAO(ServicesDAO servicesDAO) {
		this.servicesDAO = servicesDAO;
	}
	
}
