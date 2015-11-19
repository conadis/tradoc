package pe.conadis.tradoc.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ExpedienteController {
	
	private static final Logger logger = Logger.getLogger(ExpedienteController.class);

	
	@RequestMapping(value = "/mesa_parte/mesaParte.htm", method = RequestMethod.GET)
	public String cargaMesaParte(ModelMap map) {
		
		try{
			
			
		}catch(Exception e){
			logger.error("Error lista notarias"+e) ;
		}	
		
		return "mesa_parte/expedienteBuscar";
	}
	
	@RequestMapping(value = "/upAgregarExpediente.htm", method = RequestMethod.GET)
	public String upAgregarComunicado(ModelMap map) throws Exception {
		
		//Oficina oficina = new Oficina();
		
		//oficina.setFechacreacion(new Date()); 
		//map.addAttribute("oficina", oficina);
		map.addAttribute("oficina", null);
		System.out.println("en controller");
		
		//map.addAttribute("plazaList", plazaManager.getAll());
		//map.addAttribute("tipo", "agregar");
		return "mesa_parte/expedienteAgregar";
	}
	
}
