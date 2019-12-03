package com.sgj.sensores.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sgj.sensores.modelo.Sensor;

public interface DaoSensor extends JpaRepository<Sensor,Integer> {
	//objetivo daoSensor.cambiarEstado(idSensor,estado);
	@Query("select s From Sensor s Where s.cliente.id=?1")
	List<Sensor> sensorPorIdCliente(int idCliente);
	
}
