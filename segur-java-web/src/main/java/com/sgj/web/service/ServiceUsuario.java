package com.sgj.web.service;

import java.util.List;

import com.sgj.web.model.Usuario;

public interface ServiceUsuario {

	Usuario save(Usuario usuario);

	List<Usuario> findAll();

	Usuario findByUsuario(String nombreUsuario);

}
