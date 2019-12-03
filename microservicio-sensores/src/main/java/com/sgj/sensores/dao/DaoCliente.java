//package com.sgj.sensores.dao;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import com.sgj.sensores.modelo.Cliente;
//import com.sgj.sensores.modelo.Estado;
//
//public interface DaoCliente extends JpaRepository<Estado,Integer> {
//	
//	@Query("select c From Cliente c Where c.dni=?1")
//	List<Cliente> clientePorDni(String dni);
//	
//}
