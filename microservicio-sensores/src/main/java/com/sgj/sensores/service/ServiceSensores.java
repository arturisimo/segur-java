package com.sgj.sensores.service;

import java.util.List;

import com.sgj.sensores.modelo.Sensor;

public interface ServiceSensores {
	
//	public List<Alarma> saltosAlarmaPorFecha(int idCliente, Date fechaInicio, Date fechaFin);
//	public List<Alarma> saltosAlarmaPorDni(String dni);
	public List<Sensor> estadoSensores (Integer idCliente);
	//public List<Alarma> saltosAlarmaPorUsuario(Cliente cliente);
	public void activarSensor (Integer idSensor);
	public void desactivarSensor (Integer idSensor);
	public void darBajaSensor (Integer idSensor);
//	public void provocarAlarma (int idSensor);
	public void eliminarSensor (Integer idSensor);
	public void crearSensor (Sensor sensor);
	public List<Sensor> listadoByCliente(Integer id);
}
