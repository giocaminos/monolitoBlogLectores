package com.springboot.app.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.app.models.dao.IUsuarioDao;
import com.springboot.app.models.entity.Role;
import com.springboot.app.models.entity.Usuario;
import com.springboot.app.models.service.IRoleService;

@Controller
@SessionAttributes("perfil")
public class PerfilController {

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping(value = "/perfil/{name}")
	public String listtBlogsByType(@PathVariable(value = "name") String name, Map<String, Object> model,
			RedirectAttributes flash) {
		
		Map<String, String> list = new HashMap<>();
		list.put("ROLE_USER","Usuario");
		list.put("ROLE_ADMIN","Administrador y Usuario");
		
		Usuario usuario = new Usuario();
		usuario = usuarioDao.findByUsername(name);
		
		usuario.setRol("ROLE_ADMIN");
		
		model.put("titulo", "Perfil");
		model.put("entity", usuario);
		model.put("list", list);
		model.put("tituloBtn", "Guardar");
		
		
		return "perfil";
	}
	
	
	@PostMapping("/perfil/form")
	public String guardar(@Valid Usuario usuario, BindingResult result, Map<String, Object> model,
			RedirectAttributes flash) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		boolean admin = authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
		if (!admin) {
			roleService.save(new Role(null,"ROLE_ADMIN",usuario));
		}

		flash.addFlashAttribute("success", "Usaurio modificado con exito, es necesario reiniciar la Session para ver los cambios");

		return "redirect:/perfil/"+usuario.getUsername();
	}
}
