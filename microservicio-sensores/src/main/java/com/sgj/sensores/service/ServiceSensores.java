package com.sgj.sensores.service;

import java.util.List;

import com.sgj.sensores.modelo.Sensor;

public interface ServiceSensores {
	
//	public List<Alarma> saltosAlarmaPorFecha(int idCliente, Date fechaInicio, Date fechaFin);
//	public List<Alarma> saltosAlarmaPorDni(String dni);
	List<Sensor> estadoSensores (Integer idCliente);
	//public List<Alarma> saltosAlarmaPorUsuario(Cliente cliente);
	void activarSensor (Integer idSensor);
	void desactivarSensor (Integer idSensor);
	void darBajaSensor (Integer idSensor);
	void provocarAlarma (Sensor sensor) throws Exception;
	void eliminarSensor (Integer idSensor);
	void crearSensor (Sensor sensor);
	List<Sensor> listadoByCliente(Integer id);
}
