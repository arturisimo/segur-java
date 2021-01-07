package com.sgj.auth.service;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sgj.auth.model.Rol;
import com.sgj.auth.model.Usuario;
import com.sgj.auth.repository.RolRepository;
import com.sgj.auth.repository.UsuarioRepository;
import com.sgj.auth.util.Util;
import com.sgj.commons.enums.Roles;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

   @Autowired
   UsuarioRepository usuarioRepository;
   
   @Autowired
   RolRepository rolRepository;
   
   
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Usuario usuario = usuarioRepository.findByUsuario(username);
       List<Rol> roles = rolRepository.findByUsuario(usuario.getId());
       List<GrantedAuthority> authorities = roles.stream()
    		   .map(r -> new SimpleGrantedAuthority(r.getAuthority())).collect(Collectors.toList());
       
       return new org.springframework.security.core.userdetails.User(usuario.getUsuario(), usuario.getPassword(), authorities);
   }
   
   /**
	 * save cliente ROLE_USER
	 */
	@Override
	public Usuario save(Usuario usuario) {
		usuario.setPassword(Util.bcrypt(usuario.getPassword()));
		Rol rol = new Rol(0, Roles.ROLE_USER.name(), usuario);
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