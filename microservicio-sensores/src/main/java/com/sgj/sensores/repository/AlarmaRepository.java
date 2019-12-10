package com.sgj.sensores.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sgj.sensores.model.Alarma;

public interface AlarmaRepository extends JpaRepository<Alarma,Integer> {
	
	@Query("select a From Alarma a Where a.sensor.id=?1")
	List<Alarma> alarmaPorIdSensor(int idSensor);

	@Query("select a From Alarma a Where (a.fecha>=?1 and a.fecha<=?2)")
	List<Alarma> alarmasPorFecha(Date fechaInicio, Date fechaFin);
}
