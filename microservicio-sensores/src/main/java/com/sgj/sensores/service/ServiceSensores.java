package com.sgj.sensores.service;

import java.sql.Timestamp;
import java.util.List;

import com.sgj.sensores.modelo.Alarma;
import com.sgj.sensores.modelo.Cliente;
import com.sgj.sensores.modelo.Sensor;

public interface ServiceSensores {
	
	public List<Alarma> saltosAlarmaPorFecha(int idCliente, Timestamp fechaInicio, Timestamp fechaFin);
	public List<Alarma> saltosAlarmaPorDni(String dni);
	public List<Sensor> estadoSensores (Cliente cliente);
	public List<Alarma> saltosAlarmaPorUsuario(Cliente cliente);
	public void activarSensor (int idSensor);
	public void desactivarSensor (int idSensor);
	public void darBajaSensor (int idSensor);
	public void provocarAlarma (int idSensor);
	public void eliminarSensor (int idSensor);
	public void crearSensor (Sensor sensor);
	public List<Sensor> listadoByCliente(Integer id);
}
