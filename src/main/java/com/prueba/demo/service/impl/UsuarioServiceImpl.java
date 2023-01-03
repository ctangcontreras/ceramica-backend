package com.prueba.demo.service.impl;


import java.util.List;


import com.prueba.demo.core.inputDto.UsuarioInputDto;
import com.prueba.demo.core.model.Usuario;
import com.prueba.demo.core.outputDto.ListaUsuarioOutputDto;
import com.prueba.demo.mapper.UsuarioMapper;
import com.prueba.demo.service.UsuarioService;
import com.prueba.demo.support.dto.Respuesta;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioServiceImpl implements UsuarioService {
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);


	@Autowired
	private UsuarioMapper usuarioMapper;
 
	@Override
	public Respuesta<?> getListaUsuario(UsuarioInputDto param) throws Exception {
			Usuario usuario = new Usuario();
			usuario.setUsuario(param.getUsuario());
			usuario.setPassword(param.getPassword());
			List<Usuario> lista = usuarioMapper.getListaUsuario(usuario);

		if (lista != null && !lista.isEmpty()) {

			ListaUsuarioOutputDto listaUsuarioOutputDto = new ListaUsuarioOutputDto();
			listaUsuarioOutputDto.setIdUsuario(lista.get(0).getIdUsuario());
			listaUsuarioOutputDto.setIdSucursal(lista.get(0).getIdSucursal());
			listaUsuarioOutputDto.setIdPerfil(lista.get(0).getIdPerfil());
			listaUsuarioOutputDto.setPassword(lista.get(0).getPassword());
			listaUsuarioOutputDto.setUsuario(lista.get(0).getUsuario());
			listaUsuarioOutputDto.setNombres(lista.get(0).getNombres());
			listaUsuarioOutputDto.setApellidoMaterno(lista.get(0).getApellidoMaterno());
			listaUsuarioOutputDto.setApellidoPaterno(lista.get(0).getApellidoPaterno());

			Respuesta resp = new Respuesta<>();
			resp.setSuccess(true);
            resp.setMessage("Se validaron correctamente los datos");
            resp.setDato(listaUsuarioOutputDto);
            return resp;
		} else {	
			Respuesta resp = new Respuesta<>();
            resp.setSuccess(false);
            resp.setMessage("No se encontró registros");
            return resp;
		} 
  }

}