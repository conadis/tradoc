package pe.conadis.tradoc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.grupobbva.bc.per.tele.ldap.serializable.IILDPeUsuario;

import pe.conadis.tradoc.entity.MenuSistema;

import pe.conadis.tradoc.entity.Usuario;
import pe.conadis.tradoc.service.UsuarioManager;

@Controller
public class LoginController {
	
	private static final Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private UsuarioManager usuarioManager;

	@RequestMapping(value = "/login/page.htm", method = RequestMethod.GET)
	public String cargaLogin(ModelMap map) {
		map.addAttribute("usuario", new Usuario());
		return "usuarios/login";
	}
	
	@RequestMapping(value = "/login/header.htm", method = RequestMethod.GET)
	public String cargaHeader(ModelMap map, HttpServletRequest request) {
		IILDPeUsuario usuario= new IILDPeUsuario();
		if(usuario != null){		
			String idUsuario = usuario.getUID();
			String nombre    = usuario.getNombre();
			String apellido1 = usuario.getApellido1();
			String apellido2 = usuario.getApellido2();
			String user =  nombre + " " + apellido1 + " " + apellido2;
			request.getSession().setAttribute("usuario", user);
			request.getSession().setAttribute("idusuario", idUsuario);
		}
		
		
		
		
		return "menu/header";
	}
	
	@RequestMapping(value = "/login/menu.htm", method = RequestMethod.GET)
	public String cargaMenu(ModelMap map) {
		
		return "menu/menu";
	}
	
	@RequestMapping(value = "/login/bienvenida.htm", method = RequestMethod.GET)
	public String cargaBienvenida(ModelMap map) {
		
		return "menu/bienvenida";
	}

//	@RequestMapping(value = "/login/login.htm", method = RequestMethod.POST)
//	public String verifyPass(@ModelAttribute(value = "usuario") Usuario usuario,
//			BindingResult result, HttpServletRequest request) {
//
//		try {
//			usuario = usuarioManager.verifyPass(usuario);
//			
//			request.getSession().setAttribute("usuario", usuario);
//			logger.debug("Ingreso exitoso");
//			
//		} catch (Exception e) {
//			
//			logger.error("Error en login ->"+e);
//		}
//
//		if (usuario == null) {
//			return "redirect:/";
//		}
//
//		return "menu/plantilla";
//	}
	
	@RequestMapping(value = "/login/login.htm", method = RequestMethod.GET)
	public String verifyPass(HttpServletRequest request) {
		String strCodUsuario = request.getParameter("codUsuario");
		List<MenuSistema> lstMenuSistema =  new ArrayList<MenuSistema>();
		try {
			if (strCodUsuario!=null){
				lstMenuSistema = usuarioManager.obtnerMenuPorUsuario(strCodUsuario);	
			}
			lstMenuSistema = this.excluirMenuRepetido(lstMenuSistema);
			request.getSession().setAttribute("lstMenuSistema", lstMenuSistema);
		} catch (Exception e) {
			
			logger.error("Error en login ->"+e);
		}

		return "menu/plantilla";
	}
	
	private List<MenuSistema> excluirMenuRepetido(
			List<MenuSistema> lstMenuSistema) {
		List<MenuSistema> lstResultado = new ArrayList<MenuSistema>();
		for (MenuSistema menuSistema : lstMenuSistema) {
			if(!lstResultado.contains(menuSistema)){
				lstResultado.add(menuSistema);
			}
		}
		return lstResultado;
	}

	@RequestMapping(value = "/login/logout.htm", method = RequestMethod.GET)
	public String salida(ModelMap map, HttpServletRequest request) {
		
		request.getSession().removeAttribute("usuario");
		
		return "redirect:/";
	}
}
