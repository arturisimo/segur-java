package com.sgj.sensores.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgj.sensores.dao.DaoEstado;
import com.sgj.sensores.dao.DaoSensor;
import com.sgj.sensores.modelo.Estado;
import com.sgj.sensores.modelo.Sensor;

@Transactional
@Service(value = "serviceSensores")
public class ServiceSensoresImpl implements ServiceSensores {
//	@Autowired
//	DaoAlarma daoAlarma;
//	@Autowired
//	DaoCliente daoCliente;
	@Autowired
	DaoEstado daoEstado;
	@Autowired
	DaoSensor daoSensor;
	
//	@Override	
//	public List<Alarma> saltosAlarmaPorFecha(int idCliente, Timestamp fechaInicio, Timestamp fechaFin){
//		return daoAlarma.alarmasPorFecha(fechaInicio, fechaFin);
//	}
//
//	@Override		
//	public List<Alarma> saltosAlarmaPorDni(String dni){
//		//Falta la select Buscar cliente por DNI y sustituir la linea siguiente por el	
//		List<Cliente> cliente = daoCliente.clientePorDni(dni);
//		return saltosAlarmaPorUsuario(cliente.get(0));
//	}

//	@Override	
//	public List<Alarma> saltosAlarmaPorUsuario(Cliente cliente){
//		
//		// Si Fuera EAGLE --> List<Sensor> listaSensores = cliente.getSensores(); // en caso contrario query personalizada de abajo
//		 List<Sensor> listaSensores = daoSensor.sensorPorIdCliente(cliente.getId());
//		
//		//recorrer cada sensor y ver si tiene 1 o mas -> Lista saltos de alarma.
//		List<Alarma> listaAlarmasAux = null;
//		List<Alarma> listaAlarmasFinal = new ArrayList<Alarma>();
//		if(listaSensores!=null) {
//			for(Sensor s : listaSensores) {
//				//Si fuera EAGLE --> listaAlarmasAux = s.getAlarmas(); // en caso contrario query personalizada de abajo
//				listaAlarmasAux = daoAlarma.alarmaPorIdSensor(s.getId());
//				if(listaAlarmasAux != null) {
//					for(Alarma a : listaAlarmasAux){
//						listaAlarmasFinal.add(new Alarma(a.getFecha(),a.getPolicia(),a.getSensor()));
//					}
//				}
//			}
//		}
//		return listaAlarmasFinal;
//	}
	
	@Override	
	public List<Sensor> estadoSensores (Integer idCliente){	
		// si fuera EAGLE --> return cliente.getSensores(); --> al no serlo query concreta
		return daoSensor.sensorPorIdCliente(idCliente);
	}
	@Override
	public void crearSensor (Sensor sensor){
		if(!daoSensor.existsById(sensor.getId()))
		daoSensor.save(sensor);
	}
	
	@Override	
	public void activarSensor (Integer idSensor){
		
		if(daoSensor.existsById(idSensor)) {
			int estado = 3; // 3 -> estado activado
			Sensor sensor;
			try {
				sensor = daoSensor.findById(idSensor).orElseThrow(()-> new Exception("No existe el sensor con" + idSensor));
				Estado estadoFinal = daoEstado.getOne(estado);
				sensor.setEstadoBean(estadoFinal);
				daoSensor.save(sensor);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		};
	}
	@Override	
	public void desactivarSensor (Integer idSensor){
		if(daoSensor.existsById(idSensor)) {
			int estado = 2; // 2 --> estado desactivado
		if(daoSensor.existsById(idSensor)) {
				Sensor sensor;
				try {
					sensor = daoSensor.findById(idSensor).orElseThrow(()-> new Exception("NO"));
					Estado estadoFinal = daoEstado.getOne(estado);
					sensor.setEstadoBean(estadoFinal);
					daoSensor.save(sensor);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			};
		};
	}
	@Override	
	public void darBajaSensor (Integer idSensor){ 
		if(daoSensor.existsById(idSensor)) {
			int estado = 1; // 1 --> estado baja
		if(daoSensor.existsById(idSensor)) {
				Sensor sensor;
				try {
					sensor = daoSensor.findById(idSensor).orElseThrow(()-> new Exception("NO"));
					// sensor.setEstadoBean(new Estado(estado,"baja")); // ALTERNATIVA 1
					// alternativa 2 hacer una query para obtener el estado y meterle el resultado en el new Estado()
					Estado estadoFinal = daoEstado.getOne(estado);
					sensor.setEstadoBean(estadoFinal);
					daoSensor.save(sensor);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			};
		};
	}	
//	@Override	
//	public void provocarAlarma (int idSensor){ // Update estado
//		if(daoSensor.existsById(idSensor)) {
//			int estado = 4; // 4 --> estado Alarma
//		if(daoSensor.existsById(idSensor)) {
//				Sensor sensor;
//				try {
//					sensor = daoSensor.findById(idSensor).orElseThrow(()-> new Exception("NO"));
//					Estado estadoFinal = daoEstado.getOne(estado);
//					// FASE 1: Cambiar el estado del sensor a modo Alarma si procede(si esta de baja o desactivado no puede ser)
//					if(estadoFinal.getId()>2) { // es decir ni esta de baja ni desactivado	
//						sensor.setEstadoBean(estadoFinal);
//						daoSensor.save(sensor);
//					
//					// FASE 2: Consultar si el cliente tiene activado el servicio policia en caso afirmativo suscribirle al servicio FLUX policia.	
//										
//						Cliente cli = sensor.getCliente();
//						byte estadoPolicia = cli.getEstado();
//						
//						// Suscripcion al FLUX de comisaria
//						//if(estadoPolicia == 1) {
//						//}						
//
//					// FASE 3: Inscribir ese salto de alarma en el registro de alarmas(tabla alarmas)
//						
//						daoAlarma.save(new Alarma(
//										new Timestamp((long)(new Date()).getTime())
//										,estadoPolicia
//										,sensor));			
//					}
//		
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			};
//		};
//	}		
		
	@Override	
	public void eliminarSensor (Integer idSensor){
		if(daoSensor.existsById(idSensor)) {
		daoSensor.deleteById(idSensor);
		}

	}

	@Override
	public List<Sensor> listadoByCliente(Integer id) {
		return daoSensor.sensorPorIdCliente(id);
	}

	
	
	

}
