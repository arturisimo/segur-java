package com.sgj.sensores.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sgj.sensores.modelo.Estado;

public interface DaoEstado extends JpaRepository<Estado,Integer> {
	

}
