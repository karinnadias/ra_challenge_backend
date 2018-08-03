package com.ra.challenge.api.services;

import java.util.List;

import com.ra.challenge.api.documents.Complain;

public interface ComplainService {

	List<Complain> listAll();
	
	Complain listById(String id);
	
	Complain listByLocale(Complain complain);
	
	Complain create(Complain complain);
	
	Complain update(Complain complain);
	
	void remove(String id);

	
}
