package com.sgj.sensores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sgj.sensores.model.Sensor;

public interface SensorRepository extends JpaRepository<Sensor,Integer> {
	
	@Query("select s From Sensor s Where s.idCliente=?1 and s.estado !=0")
	List<Sensor> sensorPorIdCliente(int idCliente);

	List<Sensor> findByIdCliente(Integer id);

}
