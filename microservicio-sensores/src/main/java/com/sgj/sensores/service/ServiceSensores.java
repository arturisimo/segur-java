package com.sgj.sensores.service;

import java.util.List;

import com.sgj.sensores.model.Sensor;

public interface ServiceSensores {
	
	List<Sensor> listadoByCliente(Integer id) throws Exception;
	void eliminarSensor (Integer idSensor) throws Exception;
	void crearSensor (Sensor sensor) throws Exception;
	void actualizarSensor(Sensor sensor, boolean aviso) throws Exception;
	void actualizarEstadoSensor(Integer id)  throws Exception;
	List<Sensor> findByCliente(Integer id);
	
}
