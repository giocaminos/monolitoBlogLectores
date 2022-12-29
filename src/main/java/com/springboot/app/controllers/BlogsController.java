package com.springboot.app.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.app.models.dao.IUsuarioDao;
import com.springboot.app.models.entity.Blogs;
import com.springboot.app.models.entity.LectorBlog;
import com.springboot.app.models.entity.Usuario;
import com.springboot.app.models.entity.UsuarioBlog;
import com.springboot.app.models.service.IBlogsService;
import com.springboot.app.models.service.ILectorBlogService;
import com.springboot.app.models.service.ITypeBlogService;
import com.springboot.app.models.service.IUsuarioBlogService;

@Controller
@SessionAttributes("blogs")
public class BlogsController {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private IBlogsService blogservice;

	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	private IUsuarioBlogService usuarioBlogService;

	@Autowired
	private ITypeBlogService typeBlogService;

	@Autowired
	private ILectorBlogService lectorBlogService;

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping(value = { "/blogs", "/" })
	public String listtBlogs(Map<String, Object> model, RedirectAttributes flash) {
		List<Blogs> listBlog = blogservice.findAll();
		model.put("listBlog", listBlog);
		model.put("titulo", "Lista de Blogs");

		List<UsuarioBlog> listUsarioBlog = new ArrayList<UsuarioBlog>();
		for (Blogs blog : listBlog) {
			UsuarioBlog usuarioBlog = new UsuarioBlog();
			usuarioBlog = hasRole("ROLE_ADMIN", blog.getId());
			if (usuarioBlog != null) {
				listUsarioBlog.add(usuarioBlog);
			}
		}
		model.put("usuarioBlog", listUsarioBlog);

		return "listarBlog";
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping(value = "/blogs/type/{id}")
	public String listtBlogsByType(@PathVariable(value = "id") Integer id, Map<String, Object> model,
			RedirectAttributes flash) {
		List<Blogs> listBlog = blogservice.findAllBlogsByTypeBlog(id);
		List<UsuarioBlog> listUsarioBlog = new ArrayList<UsuarioBlog>();

		String titulo = "Lista de ";

		if (!listBlog.isEmpty()) {
			titulo += listBlog.get(0).getTypeBlog().getName();
			for (Blogs blog : listBlog) {
				UsuarioBlog usuarioBlog = new UsuarioBlog();
				usuarioBlog = hasRole("ROLE_ADMIN", blog.getId());
				if (usuarioBlog != null) {
					listUsarioBlog.add(usuarioBlog);
				}
			}
		} else {
			titulo = "No hay datos que mostrar";
		}

		model.put("listBlog", listBlog);
		model.put("titulo", titulo);
		model.put("usuarioBlog", listUsarioBlog);

		return "listarBlog";

	}

	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/eliminar/{idblog}/{idusuarioblog}")
	public String eliminar(@PathVariable(value = "idblog") Integer idblog,
			@PathVariable(value = "idusuarioblog") Integer idusuarioblog, RedirectAttributes flash) {

		if (idusuarioblog > 0) {
			usuarioBlogService.delete(idusuarioblog);
			blogservice.delete(idblog);
			flash.addFlashAttribute("success", "Registro eliminado con éxito!");

		}
		return "redirect:/blogs";
	}

	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/blog/form/editar/{idblog}")
	public String editarBlog(@PathVariable(value = "idblog") Integer idblog, Map<String, Object> model,
			RedirectAttributes flash) {
		Blogs blog = null;
		if (hasRole("ROLE_ADMIN", idblog) != null) {
			blog = blogservice.findById(idblog);
			model.put("titulo", "Editar " + blog.getName());
			model.put("list", typeBlogService.findAll());
		} else {
			model.put("titulo", "No Puedes Editar este registro");
			flash.addFlashAttribute("error", "Notienes acceso a edicion");
		}
		model.put("entity", blog);
		model.put("tituloBtn", "Guardar Cambios");

		return "blog/form";
	}

	@Secured({  "ROLE_ADMIN" })
	@RequestMapping(value = "/blog/form/nuevo")
	public String nuevoBlog(Map<String, Object> model, RedirectAttributes flash) {
		Blogs blog = new Blogs();
		model.put("titulo", "Nuevo blog");
		model.put("list", typeBlogService.findAll());
		model.put("entity", blog);
		model.put("tituloBtn", "Guardar Cambios");

		return "blog/form";
	}

	@Secured({  "ROLE_ADMIN" })
	@PostMapping("/form")
	public String guardar(@Valid Blogs blog, BindingResult result, Map<String, Object> model,
			RedirectAttributes flash) {

		boolean nuevoRegistro = blog.getId() == null ? true : false;
		String mensaje = "Datos guardados con éxito!";

		Blogs newBlog = blogservice.save(blog);

		if (nuevoRegistro) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Usuario usuario = new Usuario();
			usuario = usuarioDao.findByUsername(auth.getName());

			UsuarioBlog usuarioBlog = new UsuarioBlog();
			usuarioBlog.setBlogs(newBlog);
			usuarioBlog.setUsuario(usuario);
			usuarioBlogService.save(usuarioBlog);

			mensaje = "Nuevo Blog Creado";
		}

		flash.addFlashAttribute("success", mensaje);

		return "redirect:/blog/form/editar/" + newBlog.getId();
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/ver/{idblog}")
	public String verBlog(@PathVariable(value = "idblog") Integer idblog, Map<String, Object> model,
			RedirectAttributes flash) {
		
		Blogs blog = blogservice.findById(idblog);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		LectorBlog lectorBlog = new LectorBlog(null, blog.getTypeBlog().getName(), blog.getName(), auth.getName() , "");
		lectorBlogService.save(lectorBlog);
		
		model.put("entity", blog);
		model.put("titulo", "Listado de Visitas por Blog");

		return "blog";
	}
	
	@Secured({ "ROLE_ADMIN" })
	@GetMapping(value = "/lectorblog")
	public String lectorBlog(Map<String, Object> model, RedirectAttributes flash) {

		List<LectorBlog> listLectorBlog = lectorBlogService.findAllLectorBlog();
		
		model.put("listLectorBlog", listLectorBlog);
		model.put("titulo", "Listado de Visitas por Blog");

		return "listLectorBlog";
	}
	
	private UsuarioBlog hasRole(String role, Integer blogID) {

		SecurityContext context = SecurityContextHolder.getContext();

		if (context == null) {
			return null;
		}

		Authentication auth = context.getAuthentication();

		if (auth == null) {
			return null;
		}

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

		boolean admin = authorities.contains(new SimpleGrantedAuthority(role));

		if (admin) {
			Usuario u = usuarioDao.findByUsername(auth.getName());
			u.setPassword("");
			UsuarioBlog usuarioBlog = usuarioBlogService.findByUsuarioAndBlog(u.getId(), blogID);

			if (usuarioBlog != null)
				return usuarioBlog;
		}
		return null;
	}
}
