package com.ra.challenge.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ra.challenge.api.documents.Complain;
import com.ra.challenge.api.repositories.ComplainRepository;
import com.ra.challenge.api.services.ComplainService;

@Service
public class ComplainServiceImpl implements ComplainService {
	
	@Autowired
	private ComplainRepository complainRespository;

	@Override
	public List<Complain> listAll() {
		return this.complainRespository.findAll();
	}

	@Override
	public Complain listById(String id) {
		return this.complainRespository.findOne(id);
	}

	@Override
	public Complain listByLocale(Complain complain) {
		
		return this.complainRespository.findOne(complain.getLocale());
	}

	
	@Override
	public Complain create(Complain complain) {
		return this.complainRespository.save(complain);
	}

	@Override
	public Complain update(Complain complain) {
		return this.complainRespository.save(complain);
	}

	@Override
	public void remove(String id) {
		this.complainRespository.delete(id);
	}


}
