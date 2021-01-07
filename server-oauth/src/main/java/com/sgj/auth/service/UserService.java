package com.sgj.auth.service;

import java.util.List;

import com.sgj.auth.model.Usuario;

public interface UserService {
	
	Usuario save(Usuario usuario);

	List<Usuario> findAll();

	Usuario findByUsuario(String nombreUsuario);

	void delete(Integer id);
	
}
