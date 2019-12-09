package com.sgj.policia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgj.policia.model.Policia;
import com.sgj.policia.repository.PoliciaRepository;

@Service
public class PoliciaServiceImpl implements PoliciaService {
	
	@Autowired
	PoliciaRepository policiaRepository;

	@Override
	public void save(Policia policia) {
		policiaRepository.save(policia);
	}
	
}
