package com.kodigo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodigo.model.Cliente;
import com.kodigo.repo.IClienteRepo;
import com.kodigo.repo.IGenericRepo;
import com.kodigo.service.IClienteService;

@Service
public class ClienteServiceImpl extends CRUDImpl<Cliente, Integer> implements IClienteService {

	@Autowired
	private IClienteRepo repo;

	@Override
	protected IGenericRepo<Cliente, Integer> getRepo() {
		return repo;
	}

}
