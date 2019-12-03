package com.sgj.sensores.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sgj.sensores.modelo.Alarma;

public interface DaoAlarma extends JpaRepository<Alarma,Integer> {
	
	@Query("select a From Alarma a Where (a.fecha>=?1 and a.fecha<=?2)")
	List<Alarma> alarmasPorFecha(Timestamp fechaInicio, Timestamp fechaFin);
	
	@Query("select a From Alarma a Where a.sensor.id=?1")
	List<Alarma> alarmaPorIdSensor(int idSensor);
}
