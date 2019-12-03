package com.sgj.sensores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sgj.sensores.modelo.Sensor;
import com.sgj.sensores.service.ServiceSensores;

@RestController
@CrossOrigin(origins = "*")
public class SensoresController {
	
	@Autowired
	ServiceSensores serviceSensores;
	
	
//	@PostMapping(value="listadoAlarmas",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
//	public List<Alarma> saltosAlarmaPorUsuario(@RequestBody Cliente c){		
//				return serviceSensores.saltosAlarmaPorUsuario(c);
//	}	
//	
//	@GetMapping(value="listadoAlarmas/{dni}",produces=MediaType.APPLICATION_JSON_VALUE)
//	public List<Alarma> saltosAlarmaPorDni(@PathVariable("dni") String dni){		
//		return serviceSensores.saltosAlarmaPorDni(dni);
//	}

	@GetMapping(value="sensores/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Sensor> estadoSensores (@PathVariable("id") Integer id) {
		return serviceSensores.listadoByCliente(id);
	}
	
//	@GetMapping(value="listadoFechas/{idCliente}/{fechaInicio}/{fechaFin}",produces=MediaType.APPLICATION_JSON_VALUE)
//	public List<Alarma> saltosAlarmaPorFecha(@PathVariable("idCliente") int idCliente,@PathVariable("fechaInicio") Timestamp fechaInicio,@PathVariable("fechaFin") Timestamp fechaFin){
//		return serviceSensores.saltosAlarmaPorFecha(idCliente,fechaInicio,fechaFin);
//	}	
	
	@PutMapping(value="activarSensor/{idSensor}")
	public void activarSensor (@PathVariable("idSensor") int idSensor){		
		serviceSensores.activarSensor(idSensor);
	}

	@PutMapping(value="desactivarSensor/{idSensor}")
	public void desactivarSensor (@PathVariable("idSensor") int idSensor){		
		serviceSensores.desactivarSensor(idSensor);
	}
	
	@PutMapping(value="darBajaSensor/{idSensor}")
	public void darBajaSensor (@PathVariable("idSensor") int idSensor){		
		serviceSensores.darBajaSensor(idSensor);
	}
//	
//	@PutMapping(value="provocarAlarma/{idSensor}")
//	public void provocarAlarma (@PathVariable("idSensor") int idSensor){		
//		serviceSensores.provocarAlarma(idSensor);
//	}
		
	@DeleteMapping(value="eliminarSensor/{idSensor}")
	public void eliminarSensor (@PathVariable("idSensor") int idSensor){		
		serviceSensores.eliminarSensor(idSensor);
	}
		
	@PostMapping(value="crearSensor",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void crearSensor (@RequestBody Sensor s) {
		serviceSensores.crearSensor(s);
	}

		
}
