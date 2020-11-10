package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.PuntosDao;
import com.example.demo.model.Puntos;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class ControladorQuiz {
	
	private int general=0;
	private int homer=0;
	private int lisa=0;
	private int bart=0;
	private int marge=0;
	private String nombre;
	
	@Autowired
	private PuntosDao puntosDao;
	
	
	@GetMapping("/inicio")
	public String process(Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if (messages == null) {
			messages = new ArrayList<>();
		}
		model.addAttribute("sessionMessages", messages);

		return "inicio";
	}

	@PostMapping("/datos")
	public String persistMessage(Model model,
			@RequestParam("nombre") String nombre,
			@RequestParam("correo") String correo,
			@RequestParam("contra") String contra,
			HttpServletRequest request) {
		
		@SuppressWarnings("unchecked")
		List<String> messages = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
		if (messages == null) {
			messages = new ArrayList<>();
			request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
		}
		
		model.addAttribute("datosUsuario", messages);
		messages.add(nombre);
		request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
		
		if (nombre == "" || correo == "" || contra == "") {
			return "redirect:/inicio";
		}
		
		this.nombre=nombre; 
		return "pag1";	
	}
	
	
	@PostMapping("/pag1")
	public String pagUno(Model model, 
			@RequestParam char ac) {
		
		if (ac == 'h') {
			homer+=1;
		} else if (ac == 'b') {
			bart+=1;
		}else if (ac == 'm') {
			marge+=1;
		}else {
			lisa+=1;
		}

		//model.addAttribute("actividad_form", actividad);
		
		return "pag2";
	}
	
	@PostMapping("/pag2")
	public String pagDos(Model model, 
			@RequestParam char asignatura) {
		
		if (asignatura == 'h') {
			homer+=1;
		} else if (asignatura == 'b') {
			bart+=1;
		}else if (asignatura == 'm') {
			marge+=1;
		}else {
			lisa+=1;
		}
		
		
		//model.addAttribute("asignatura_form", asignatura);
		return "pag3";
	}
	
	@PostMapping("/pag3")
	public String pagTres(Model model, 
			@RequestParam char animal) {
		
		if (animal == 'h') {
			homer+=1;
		} else if (animal == 'b') {
			bart+=1;
		}else if (animal == 'm') {
			marge+=1;
		}else {
			lisa+=1;
		}
		
		//model.addAttribute("asignatura_form", animal);
		return "pag7";
	}
	
	@PostMapping("/pag7")
	public String pagSiete(Model model, 
			@RequestParam String comida) {
		
		if (comida.equalsIgnoreCase("donut")) {
			homer+=1;
		} else if (comida.equalsIgnoreCase("hamburguesa")) {
			bart+=1;
		}else if (comida.equalsIgnoreCase("espaguetis")) {
			marge+=1;
		}else {
			lisa+=1;
		}
		
		//model.addAttribute("asignatura_form", animal);
		return "pag5";
	}
	
	@PostMapping("/pag5")
	public String pagCinco(Model model, 
			@RequestParam char miedo) {
		
		if (miedo == 'h') {
			homer+=1;
		} else if (miedo == 'b') {
			bart+=1;
		}else if (miedo == 'm') {
			marge+=1;
		}else {
			lisa+=1;
		}
		
		
		
		//model.addAttribute("asignatura_form", miedo);
		return "pag6";
	}
	
	@PostMapping("/pag6")
	public String pagSeis(Model model, 
			@RequestParam char habitacion) {
		
		if (habitacion == 'h') {
			homer+=1;
		} else if (habitacion == 'b') {
			bart+=1;
		}else if (habitacion == 'm') {
			marge+=1;
		}else {
			lisa+=1;
		}
		
		
		
		//model.addAttribute("asignatura_form", animal);
		return "pag4";
	}
	
	@PostMapping("/pag4")
	public String pagCuatro(Model model, 
			@RequestParam char de) {
		
		if (de == 'h') {
			homer+=1;
		} else if (de == 'b') {
			bart+=1;
		}else if (de == 'm') {
			marge+=1;
		}else {
			lisa+=1;
		}
		
		
		if (homer > lisa && homer > bart && homer > marge) {
			general=1;
		}else if (bart > homer && bart > marge && bart > lisa) {
			general=2;
		}else if (marge > homer && marge > bart && marge > lisa) {
			general=3;
		}else if (lisa > homer && lisa > bart && lisa > marge) {
			general=4;
		}else {
			general = 0;
		}
		
		
		Puntos punto= new Puntos(nombre, general);
		puntosDao.save(punto);
		List<Puntos> puntos= puntosDao.findAll();
		
		
		model.addAttribute("puntos", puntos);
		model.addAttribute("homer_form", homer);
		model.addAttribute("general_form", general);
		
		//model.addAttribute("asignatura_form", miedo);
		return "pagFinal";
	}
	
	
	
	
	
	@PostMapping("/destroy")
	public String destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		
		general=0;
		lisa=0;
		homer=0;
		bart=0;
		marge=0;
		return "redirect:/inicio";
	}

}
