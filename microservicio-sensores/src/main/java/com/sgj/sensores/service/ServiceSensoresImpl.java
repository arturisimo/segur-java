package com.sgj.sensores.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sgj.sensores.model.Alarma;
import com.sgj.sensores.model.Sensor;
import com.sgj.sensores.repository.AlarmaRepository;
import com.sgj.sensores.repository.SensorRepository;

@Transactional
@Service(value = "serviceSensores")
public class ServiceSensoresImpl implements ServiceSensores {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceSensoresImpl.class);
	
	@Autowired
	AlarmaRepository alarmaRepository;
	
	@Autowired
	SensorRepository sensorRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${url.servicio.policia}")
	private String urlPolicia;
	
	@Autowired
	private KafkaTemplate<String, Sensor> kafkaTemplate;
	
	@Value("${spring.application.name}")
	private String serviceName;
	
	@Value("${spring.kafka.topic}")
	private String topic;
	
	
	public static enum EstadoSensor {
		/** sensor quitado */
		BAJA,
		DESACTIVADO,
		ACTIVADO,
		ALARMA
	};
	
	public static enum Estancia {
		COCINA("Cocina"),
		GARAJE("Garaje"),
		PASILLO("Pasillo"),
		DORMITORIO("Dormitorio"),
		SALON("Salón");
		
		private String nombre;
		
		private Estancia(String nombre) {
			this.nombre = nombre;
		}

		public String getNombre() {
			return nombre;
		}
		
	};
	
	@Override
	public List<Sensor> listadoByCliente(Integer id) throws Exception {
		return sensorRepository.sensorPorIdCliente(id);
	}
	
	@Override
	public List<Sensor> findByCliente(Integer id) {
		try {
			return sensorRepository.findByIdCliente(id).stream()
					.filter(s-> !s.getEstado().equals(EstadoSensor.BAJA))
					.collect(Collectors.toList());	
		} catch (Exception e) {
			return new ArrayList<>();
		} 
	}
	
	@Override	
	public void eliminarSensor (Integer id) throws Exception {
		Sensor sensor = sensorRepository.findById(id).orElseThrow(() -> new Exception("No existe este sensor"));
		sensor.setEstado(EstadoSensor.BAJA);
		sensorRepository.save(sensor);
	}
	
	@Override
	public void altaSensor (Sensor sensorPost) throws Exception{
		Sensor sensor = sensorRepository.findByIdCliente(sensorPost.getIdCliente()).stream()
								.filter(s->s.getZona().equals(sensorPost.getZona()))
								.findFirst()
								.orElse(sensorPost);
		sensor.setEstado(EstadoSensor.DESACTIVADO);
		sensorRepository.save(sensor);
	}
	
	@Override
	public void actualizarSensor(Sensor sensor, boolean aviso) throws JsonMappingException, JsonProcessingException{
		
		try {
		
			if (sensor.getEstado().equals(EstadoSensor.ALARMA)) {
				List<Alarma> alarmas = sensor.getAlarmas();
				alarmas.add(new Alarma(new Date(), true, sensor));
				sensorRepository.save(sensor);
				if (aviso) {
					produceMessage(sensor);
					
					
//					HttpHeaders headers = new HttpHeaders();
//					headers.setContentType(MediaType.APPLICATION_JSON);
//					HttpEntity<String> request = new HttpEntity<>(sensor.getDireccion(), headers);				
//				
//						ResponseEntity<String> response = restTemplate.exchange(urlPolicia, HttpMethod.POST, request, String.class);
//								
//						ObjectMapper mapper = new ObjectMapper();
//						ResponseJson responseJson = mapper.readValue(response.getBody(), ResponseJson.class);
//						
//						if (!responseJson.isSuccess()) {
//							throw new ServiceException("No se ha podido efectuar la reserva. Error " + responseJson.getMessage());
//						}
				}
			} else {
				sensorRepository.save(sensor);
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
	}
	
	private void produceMessage(Sensor sensor) {
		try {
			kafkaTemplate.send(topic, sensor);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}
	
	
	@Override
	public Sensor actualizarEstadoSensor(Integer id) throws Exception {
		Sensor sensor = sensorRepository.findById(id).orElseThrow(() -> new Exception("No existe este sensor"));
		sensor.setEstado(EstadoSensor.DESACTIVADO.equals(sensor.getEstado()) ? EstadoSensor.ACTIVADO : EstadoSensor.DESACTIVADO);
		sensorRepository.save(sensor);
		return sensor;
	}

	/**
	 * TODO flatmap
	 */
	@Override
	public List<Alarma> listadoAlarmasByCliente(Integer id) {
		List<Sensor> sensores = sensorRepository.sensorPorIdCliente(id);
		List<Alarma> alarmas = new ArrayList<>();
		for (Sensor sensor : sensores) {
			alarmas.addAll(sensor.getAlarmas());
		}
		return alarmas;
	}
	
	
	@Override	
	public List<Alarma> saltosAlarmaPorFecha(int idCliente, Date fechaInicio, Date fechaFin){
		return alarmaRepository.alarmasPorFecha(fechaInicio, fechaFin);
	}
/*
	@Override		
	public List<Alarma> saltosAlarmaPorDni(String dni){
		List<Cliente> cliente = clienteRepository.findByDni(dni);
		return saltosAlarmaPorUsuario(cliente.get(0));
	}

	private List<Alarma> saltosAlarmaPorUsuario(Cliente cliente){
		
		 //Si Fuera EAGLE --> List<Sensor> listaSensores = cliente.getSensores();  en caso contrario query personalizada de abajo
		 List<Sensor> listaSensores = sensorRepository.sensorPorIdCliente(cliente.getId());
		
		//recorrer cada sensor y ver si tiene 1 o mas -> Lista saltos de alarma.
		List<Alarma> listaAlarmasAux = null;
		List<Alarma> listaAlarmasFinal = new ArrayList<Alarma>();
		if(listaSensores!=null) {
			for(Sensor s : listaSensores) {
				//Si fuera EAGLE --> listaAlarmasAux = s.getAlarmas();  en caso contrario query personalizada de abajo
				listaAlarmasAux = alarmaRepository.alarmaPorIdSensor(s.getId());
				if(listaAlarmasAux != null) {
					for(Alarma a : listaAlarmasAux){
						listaAlarmasFinal.add(new Alarma(a.getFecha(),a.getPolicia(),a.getSensore()));
					}
				}
			}
		}
		return listaAlarmasFinal;
	}
	
	
	*/
	
}
