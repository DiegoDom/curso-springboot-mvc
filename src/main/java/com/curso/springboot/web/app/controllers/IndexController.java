package com.curso.springboot.web.app.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.springboot.web.app.models.Usuario;

// Define la clase como un controlador para peticiones
@Controller
// Define la ruta de primer nivel para los metodos del controlador
@RequestMapping("/app")
public class IndexController {
	
	@Value("${titulo.appname} | ${texto.indexcontroller.index.titulo}")
	private String tituloIndex;
	
	@Value("${titulo.appname} | ${texto.indexcontroller.perfil.titulo}")
	private String tituloPerfil;
	
	@Value("${titulo.appname} | ${texto.indexcontroller.listar.titulo}")
	private String tituloListar;

	// Define una ruta tipo get con base al primer nivel, se pueden añadir varias
	// rutas entre {}
	@GetMapping({ "/index", "/", "/home", "" })
	public String index(Model model) {
		// Model nos permite enviar datos a la vista
		model.addAttribute("titulo", tituloIndex);
		return "index";
	}

	// Tambien se puede usar para definir una ruta por defecto es tipo GET
	@RequestMapping("/perfil")
	public String perfil(Model model) {

		// Creamos una instancia de usuario y asignamos valores
		Usuario usuario = new Usuario();
		usuario.setNombre("Diego");
		usuario.setApellido("Dominguez");
		usuario.setEmail("dominguezdiex@gmail.com");

		// Enviamos el usuario a la vista ademas de un titulo
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", tituloPerfil.concat(usuario.getNombre()));

		return "perfil";
	}

	@RequestMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", tituloListar);
		return "listar";
	}

	// Agregar los datos a todos los métodos del controlador
	@ModelAttribute("usuarios")
	public List<Usuario> poblarUsuarios() {
		List<Usuario> usuarios = Arrays.asList(new Usuario("Diego", "Dominguez", "dominguezdiex@gmail.com"),
				new Usuario("Alberto", "Hernández", "hernandezalbx@gmail.com"),
				new Usuario("Taylor", "Dominguez", "domingueztayx@gmail.com"),
				new Usuario("Aliyah", "Dominguez", "dominguezalyhx@gmail.com"));
		return usuarios;
	}

}
