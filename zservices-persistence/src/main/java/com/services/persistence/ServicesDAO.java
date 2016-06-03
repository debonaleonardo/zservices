package com.services.persistence;

import java.util.List;

import com.services.model.Service;

public interface ServicesDAO {

	/**
	 * Adiciona um service na base
	 * @param Service service
	 * @throws Exception
	 * */
	void add(Service service) throws Exception;
	
	/**
	 * Retorna todos os servicos cadastrados
	 * para apresentacao
	 * @return List com os servicos localizados
	 * @throws Exception
	 * */
	List<Service> listAll() throws Exception;
	
	/**
	 * Retorna todos os servicos cadastrados
	 * para apresentacao com um determinado
	 * status
	 * @param Integer status - 1 se ativo / 0 se inativo
	 * @return List com os servicos localizados
	 * @throws Exception
	 * */
	List<Service> listAllByStatus(Integer status) throws Exception;
	
	/**
	 * Retorna servico pelo nome
	 * @param String name
	 * @return Service
	 * @throws Exception
	 * */
	Service findByName(String name) throws Exception;
}
