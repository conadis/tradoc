package pe.conadis.tradoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.conadis.tradoc.service.UsuarioManager;

@Controller
public class UsuarioController {
	
	//private static final Logger logger = Logger.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioManager usuarioManager;



	@RequestMapping("/usuario/delete/{employeeId}")
	public String deleteEmplyee(@PathVariable("employeeId") Integer employeeId) {
		// employeeManager.delete(employeeId);
		return "redirect:/";
	}


}
