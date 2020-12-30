package com.sgj.sensores.service;

import java.util.Date;
import java.util.List;

import com.sgj.sensores.model.Alarma;
import com.sgj.sensores.model.Sensor;

public interface ServiceSensores {
	
	List<Sensor> listadoByCliente(Integer id) throws Exception;
	void eliminarSensor (Integer idSensor) throws Exception;
	void altaSensor (Sensor sensor) throws Exception;
	void actualizarSensor(Sensor sensor, boolean aviso) throws Exception;
	Sensor actualizarEstadoSensor(Integer id)  throws Exception;
	List<Sensor> findByCliente(Integer id);
	List<Alarma> listadoAlarmasByCliente(Integer id);
	List<Alarma> saltosAlarmaPorFecha(int idCliente, Date fechaInicio, Date fechaFin);
	
}
