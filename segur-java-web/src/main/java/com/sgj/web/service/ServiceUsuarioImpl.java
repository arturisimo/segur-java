package com.sgj.web.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgj.web.model.Rol;
import com.sgj.web.model.Usuario;
import com.sgj.web.repository.RolRepository;
import com.sgj.web.repository.UsuarioRepository;
import com.sgj.web.util.Util;

@Service
public class ServiceUsuarioImpl implements ServiceUsuario {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	RolRepository rolRepository;
	
	/**
	 * save cliente ROLE_USER
	 */
	@Override
	public Usuario save(Usuario usuario) {
		usuario.setPassword(Util.bcrypt(usuario.getPassword()));
		Rol rol = new Rol(0, Util.Roles.ROLE_USER.name(), usuario);
		usuario.setRoles(Arrays.asList(rol));
		usuarioRepository.save(usuario);
		return usuario;
	}

	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario findByUsuario(String nombreUsuario) {
		return usuarioRepository.findByUsuario(nombreUsuario);
	}
	
	@Override
	public void delete(Integer idUsuario) {
		usuarioRepository.deleteById(idUsuario);
	}

}
