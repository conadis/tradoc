package pe.conadis.tradoc.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class DerivarDocumentoController {

	private static final Logger logger = Logger.getLogger(DerivarDocumentoController.class);

	@RequestMapping(value = "/mesa_parte/derivarDocumento.htm", method = RequestMethod.GET)
	public String cargaDerivarDocumento(ModelMap map) {
		
		try{
			
			
		}catch(Exception e){
			logger.error("Error lista notarias"+e) ;
		}	
		
		return "mesa_parte/derivarDocumentoBuscar";
	}
	
	@RequestMapping(value = "/mesa_parte/openDerivarDocumentoForm.htm", method = RequestMethod.GET)
	public String cargaDerivar(ModelMap map) {
		
		try{			
			
		}catch(Exception e){
			logger.error("Error"+e) ;
		}		
		return "mesa_parte/derivarDocumento";
	}
}
