package pe.conadis.tradoc.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.conadis.tradoc.entity.Accion;
import pe.conadis.tradoc.entity.AnularExpediente;
import pe.conadis.tradoc.entity.Documento;
import pe.conadis.tradoc.entity.EntidadExterna;
import pe.conadis.tradoc.entity.EstadoExpediente;
import pe.conadis.tradoc.entity.Expediente;
import pe.conadis.tradoc.entity.ExpedienteDocPersona;
import pe.conadis.tradoc.entity.NumeracionDocumento;
import pe.conadis.tradoc.entity.TipoDocumento;
import pe.conadis.tradoc.entity.UnidadOrganica;
import pe.conadis.tradoc.entity.Usuario;
import pe.conadis.tradoc.service.AccionManager;
import pe.conadis.tradoc.service.DerivarManager;
import pe.conadis.tradoc.service.DocumentoManager;
import pe.conadis.tradoc.service.ExpedienteManager;
import pe.conadis.tradoc.service.NumeracionDocumentoManager;
import pe.conadis.tradoc.util.Constants;
import pe.conadis.tradoc.vo.AccionVO;
import pe.conadis.tradoc.vo.DocumentoVO;
import pe.conadis.tradoc.vo.ExpedienteVO;
import pe.conadis.tradoc.vo.UnidadOrganicaVO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



@Controller
public class ExpedienteController {
	
	private static final Logger logger = Logger.getLogger(ExpedienteController.class);

	@Autowired
	private ExpedienteManager expedienteManager;
	
	@Autowired
	private DerivarManager derivarManager;
	
	@Autowired
	private DocumentoManager documentoManager;
	
	@Autowired
	private AccionManager accionManager;
	
	@Autowired
	private NumeracionDocumentoManager numeracionDocumentoManager;
	
	@RequestMapping(value = "/mesa_parte/mesaParte.htm", method = RequestMethod.GET)
	public String cargaMesaParte(ModelMap map) {
		
		try{
			ExpedienteVO expedienteVOBuscar  =new ExpedienteVO();
			map.addAttribute("expedienteVOBuscar", expedienteVOBuscar);
			
		}catch(Exception e){
			logger.error("Error lista notarias"+e) ;
		}	
		
		return "mesa_parte/expedienteBuscar";
	}
	
	@RequestMapping(value = "/upAgregarExpediente.htm", method = RequestMethod.GET)
	public String upAgregarExpediente(ModelMap map,HttpServletRequest request) throws Exception {
		
		request.getSession().removeAttribute("__Expediente_Session");
		
		Usuario usuario=(Usuario)request.getSession().getAttribute("usuarioLogin");
		
		ExpedienteVO expedienteVO  =new ExpedienteVO();
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		expedienteVO.setFecCreacion(formateador.format(new Date()));
		expedienteVO.setCodUsuario(usuario.getCodUsuario());

		map.addAttribute("expedienteVO", expedienteVO);
		
		return "mesa_parte/expedienteAgregar";
	}
	
	@RequestMapping(value = "/upAgregarDocumentoExt.htm", method = RequestMethod.GET)
	public String upAgregarDocumentoExt(ModelMap map,HttpServletRequest request) throws Exception {
		
		Usuario usuario=(Usuario)request.getSession().getAttribute("usuarioLogin");
		request.getSession().removeAttribute("__listaUO_Accion");
		
		DocumentoVO documentoVO  =new DocumentoVO();
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		documentoVO.setFechaCreacion(formateador.format(new Date()));
		documentoVO.setCodUsuCreacion(usuario.getCodUsuario());
		
		
		List<UnidadOrganica> listaUnidadOrganica = expedienteManager.listUOporUsuario(usuario);
		ArrayList<HashMap<String,String>> y = new ArrayList<HashMap<String,String>>();
		for(UnidadOrganica unidadOrganica : listaUnidadOrganica){
			HashMap<String,String> mapa = new HashMap<String,String>();
			  mapa.put("coduo",String.valueOf(unidadOrganica.getCodUo()));
			  mapa.put("desuo",unidadOrganica.getDesUo());
			  y.add(mapa);
		}			

		Gson g = new Gson();
		map.addAttribute("documentoVO", documentoVO);
		map.addAttribute("dataJsonUo", g.toJson(y));
		return "mesa_parte/expedienteDocumentoExterno";
	}
	
	
	
	
	@RequestMapping(value = "/upListarExpediente.htm", method = RequestMethod.GET)
	public String upListarExpediente(ModelMap map) throws Exception {
		
		return "mesa_parte/expedienteLista";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/upAgregarAccion.htm", method = RequestMethod.GET)
	public String upAgregarAccion(ModelMap map,HttpServletRequest request) throws Exception {
		
		List<Accion> listaAccion = accionManager.getAll();
		ArrayList<HashMap<String,String>> y = new ArrayList<HashMap<String,String>>();
		
		HashMap<String,String> mapa = null;
		for(Accion accion:listaAccion){
			mapa = new HashMap<String,String>();
			mapa.put("codacc",accion.getCodAccion().toString());
			mapa.put("desacc",accion.getDesAccion());
			y.add(mapa);
		}
		
		String uoSeleccionada = request.getParameter("uoSeleccionada");
		String accionesSelec ="";
		List<UnidadOrganicaVO> listaUOsession = (List<UnidadOrganicaVO>)request.getSession().getAttribute("__listaUO_Accion");
		if(listaUOsession!=null && uoSeleccionada!=null){
			
			for(UnidadOrganicaVO uo : listaUOsession){
				if(uoSeleccionada.equals(uo.getCodUO())){
					for(AccionVO accSeleccionadas:uo.getListaAccion()){
						accionesSelec = accionesSelec + "," + accSeleccionadas.getCodAccion();
					}
					break;
				}
				
			}
		}
		
		Gson g = new Gson();
		map.addAttribute("dataJsonAccion", g.toJson(y));
		map.addAttribute("uoSeleccionada", uoSeleccionada);
		map.addAttribute("accionSeleccionadas",accionesSelec);
			
		return "mesa_parte/expedienteAccion";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addAccion.htm", method = RequestMethod.POST)
	public @ResponseBody String addAccion(ModelMap model,HttpServletRequest request) throws Exception 
	{
		
		//Se captura las acciones seleccionadas para la UO 
		String selAcciones = request.getParameter("selAcciones");
		List<String> items = Arrays.asList(selAcciones.split("\\s*,\\s*"));
		List<AccionVO> listaAcciones = new ArrayList<AccionVO>();
		AccionVO accionVO = null;
		for(String codAccion : items){
			accionVO = new AccionVO();
			accionVO.setCodAccion(codAccion);
			listaAcciones.add(accionVO);
		}
		
		//Se obtiene  la UO y se setea las acciones seleccionadas
		String uoSeleccionada = request.getParameter("uoSeleccionada");
		UnidadOrganicaVO uoVO = new UnidadOrganicaVO();
		uoVO.setCodUO(uoSeleccionada);
		uoVO.setListaAccion(listaAcciones);
		
		
		//Se agrega la UO con sus acciones a session
		//Se obtiene la lista de UO de sesion
		List<UnidadOrganicaVO> listaUOsession = (List<UnidadOrganicaVO>)request.getSession().getAttribute("__listaUO_Accion");
		int index=0;
		if(listaUOsession!=null){
			for(UnidadOrganicaVO uo:listaUOsession){
				if(uo.getCodUO().equals(uoVO.getCodUO())){
					//Eliminamos si existiese la UO en session
					listaUOsession.remove(index);
					break;
				}
				index++;
			}
			//Agregamos la UO con las acciones nuevas seleccionadas
			listaUOsession.add(uoVO);
		}else{
			listaUOsession = new  ArrayList<UnidadOrganicaVO>();
			listaUOsession.add(uoVO);
		}
		request.getSession().setAttribute("__listaUO_Accion", listaUOsession);
		
		
		return "succes";
	}
	
	@RequestMapping(value = "/searchExpediente.htm", method = RequestMethod.POST)
	public @ResponseBody String searchExpediente(@ModelAttribute(value="expedienteVOBuscar") ExpedienteVO expedienteVOBuscar , ModelMap model,HttpServletRequest request) throws Exception 
	{
		
		request.getSession().removeAttribute("__listaUO_Accion");
		request.getSession().removeAttribute("__listaDoc_Externo");
		request.getSession().removeAttribute("__Expediente_Session");
		
		List<Expediente> listaExpediente = expedienteManager.searchListExpediente(expedienteVOBuscar);
		ArrayList<HashMap<String,String>> y = new ArrayList<HashMap<String,String>>();
		List<DocumentoVO> listaDocExternos = null;
		
		if(!listaExpediente.isEmpty()){
			if(listaExpediente.size()>=1){
				SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
				for(Expediente expediente : listaExpediente){
					HashMap<String,String> mapa = new HashMap<String,String>();
					  mapa.put("anidoc",expediente.getNumeracionDocumento().getAnioDocumento());
					  mapa.put("numdoc",expediente.getNumeracionDocumento().getNumDocumento().toString());
					  mapa.put("indexp",expediente.getNumeracionDocumento().getIndExpediente());
					  mapa.put("candia",expediente.getCanDias().toString());
					  mapa.put("desasu",expediente.getDesAsunto());
					  mapa.put("codent",expediente.getEntidadExterna().getCodEntidad().toString());
					  mapa.put("descri",expediente.getDescripcion());
					  mapa.put("desdoc",expediente.getDesDocumento());
					  mapa.put("desutd",expediente.getDesUtd());
					  mapa.put("numfol",expediente.getNumFolios().toString());
					  mapa.put("detall",expediente.getDetalle());
					  mapa.put("fecing",formateador.format(expediente.getFecCreacion()));
					  mapa.put("codexp",expediente.getCodExpediente().toString());
					  mapa.put("intere",expediente.getInteresado());
					  mapa.put("codusu",expediente.getCodUsuCreacion());
					  y.add(mapa);
					  
					  //OBTENEMOS LOS DOCUMENTOS EXTERNOS
					 String codUOusuario = request.getSession().getAttribute("UOCodigo")!=null?String.valueOf(request.getSession().getAttribute("UOCodigo")):"";
					 listaDocExternos = expedienteManager.listDocumentoExterno(expediente, codUOusuario!=null?codUOusuario:"");
					 
					 request.getSession().setAttribute("__Expediente_Session",expediente);
					 
					 break;
				}
				if(listaDocExternos!=null && !listaDocExternos.isEmpty()){
					request.getSession().setAttribute("__listaDoc_Externo", listaDocExternos);
					GsonBuilder builder = new GsonBuilder();
			        Gson gson = builder.create();
					model.addAttribute("jsonListDocExt", gson.toJson(listaDocExternos));
				}
				
				
			}
		}
		
		
		
		model.addAttribute("listaExpediente", listaExpediente);
		model.addAttribute("listaDocExternos", listaDocExternos);
		model.addAttribute("listaExpedienteSize", listaExpediente!=null?listaExpediente.size():0);
		
		
		
		
		Gson g = new Gson();
		return g.toJson(y);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addDocumentoExt.htm", method = RequestMethod.POST)
	public @ResponseBody String addDocumentoExt(@ModelAttribute(value="documentoVO") DocumentoVO documentoVO , ModelMap model,HttpServletRequest request) throws Exception 
	{
		List<DocumentoVO> listaDocExtsession = (List<DocumentoVO>)request.getSession().getAttribute("__listaDoc_Externo");
		List<UnidadOrganicaVO> listaUO = (List<UnidadOrganicaVO>)request.getSession().getAttribute("__listaUO_Accion");
		
		
		/*DocumentoVO documento = new DocumentoVO();
		documento.setCodDocumento(documentoVO.getCodDocumento());
		documento.setCodTipDocumento(Integer.parseInt(documentoVO.getCodTipDocumento()));*/
		
		documentoVO.setDocSession("S");//DOCUMENTOS QUE ESTAN EN SESSION
		ArrayList<HashMap<String,String>> y = new ArrayList<HashMap<String,String>>();
		if(listaDocExtsession!=null){
			documentoVO.setListaUO(listaUO);
			listaDocExtsession.add(documentoVO);
		}else{
			listaDocExtsession = new ArrayList<DocumentoVO>();
			documentoVO.setListaUO(listaUO);
			listaDocExtsession.add(documentoVO);
		}

		
		for(DocumentoVO doc : listaDocExtsession){
			HashMap<String,String> mapa = new HashMap<String,String>();
			  mapa.put("envpor",doc.getDesEntidadExtUO());//ENVIADO POR
			  mapa.put("dirgia",doc.getDesUO());//CONCATENAR DE LA LISTA
			  mapa.put("numdoc",doc.getDesNumDocumento());//NRO.DOCUMENTO
			  mapa.put("fecreg",doc.getFechaCreacion());//FECHA DE REGISTRO
			  mapa.put("desest",doc.getDesEstDocumento());//DESCRIPCION DE ESTADO
			  mapa.put("asunto",doc.getDescripcion());//FECHA DE REGISTRO
			  
			  y.add(mapa);
		}			

		if(listaDocExtsession!=null && !listaDocExtsession.isEmpty()){
			request.getSession().setAttribute("__listaDoc_Externo",listaDocExtsession);
		}
		
		Gson g = new Gson();
		model.addAttribute("documentoVO", documentoVO);
		model.addAttribute("dataJsonDocExt", g.toJson(y));
		
		request.getSession().removeAttribute("__listaUO_Accion");
		 
		return g.toJson(y);
	}
	

	@RequestMapping(value = "/saveExpediente.htm", method = RequestMethod.POST)
	public @ResponseBody String saveExpediente(@ModelAttribute(value="expedienteVOBuscar") ExpedienteVO expedienteVOBuscar , ModelMap model,HttpServletRequest request) throws Exception 
	{
		
		Expediente expedienteSession = (Expediente)request.getSession().getAttribute("__Expediente_Session");
		List<DocumentoVO> listaDocExtsession = (List<DocumentoVO>)request.getSession().getAttribute("__listaDoc_Externo");
		
		if(expedienteSession!=null){
			if(expedienteSession.getCodExpediente()==null){
				numeracionDocumentoManager.add(expedienteSession.getNumeracionDocumento());
				
				expedienteSession.setFecCreacion(new Date());
				expedienteManager.add(expedienteSession);
				
			}else{
				
				expedienteSession.setCodUsuModificacion(expedienteVOBuscar.getCodUsuario());
				expedienteSession.setFecModificacion(new Date());
				expedienteManager.update(expedienteSession);
			}
		} else{
			return "error";
			
		}
	

		
		return "";
	}
	@RequestMapping(value = "/addExpediente.htm", method = RequestMethod.POST)
	public @ResponseBody String addExpediente(@ModelAttribute(value="expedienteVO") ExpedienteVO expedienteVO , ModelMap model,HttpServletRequest request) throws Exception 
	{
				Expediente expediente = new Expediente();
				
				List<AnularExpediente> listaAnularExpediente = new ArrayList<AnularExpediente>();
				List<Documento> listaDocumento = new ArrayList<Documento>();
				
				//ENTIDAD_EXTERNA(FK)
				EntidadExterna entidadExterna = new EntidadExterna();
				entidadExterna.setCodEntidad(Integer.parseInt(expedienteVO.getCodEntidad()));
				
				//ENTIDAD ESTADO_EXPEDIENTE (FK)
				EstadoExpediente estadoExpediente = new EstadoExpediente();
				estadoExpediente.setCodEstadoExpediente(Constants.EstadoExpediente.ESTADO_REGISTRADO);
				
				List<ExpedienteDocPersona> listaExpedienteDocPersona = new ArrayList<ExpedienteDocPersona>();
				
				//NUMERACIÓN_EXPEDIENTE
				TipoDocumento tipoDocumento = new TipoDocumento();
				tipoDocumento.setCodTipDocumento(Constants.TipoDocumento.EXPEDIENTE);
				
				NumeracionDocumento numeracionDocumento = new NumeracionDocumento();
				numeracionDocumento.setAnioDocumento(expedienteVO.getAnioDocumento());
				numeracionDocumento.setNumDocumento(Integer.parseInt(expedienteVO.getNumDocumento()));
				numeracionDocumento.setTipoDocumento(tipoDocumento);
				numeracionDocumento.setCodUsuCreacion(expedienteVO.getCodUsuario());
				numeracionDocumento.setFecCreacion(new Date());
				numeracionDocumento.setIndExpediente(expedienteVO.getIndExpediente());
				numeracionDocumento.setIndEstado("1");
				//numeracionDocumentoManager.add(numeracionDocumento);
				
				
				
				expediente.setAnularExpedientes(listaAnularExpediente);//LIST
				expediente.setCanDias(Integer.parseInt(expedienteVO.getCanDias()));
				if(expedienteVO.getCodExpediente()!=null && !expedienteVO.getCodExpediente().isEmpty()){
					expediente.setCodExpediente(Integer.parseInt(expedienteVO.getCodExpediente()));
				}else{
					expediente.setCodExpediente(null);
				}
				
				expediente.setCodUsuCreacion(expedienteVO.getCodUsuario());
				
				expediente.setDesAsunto(expedienteVO.getDesAsunto());
				expediente.setDescripcion(expedienteVO.getDescripcion());
				expediente.setDesDocumento(expedienteVO.getDesDocumento());
				expediente.setDesUtd(expedienteVO.getDesUtd());
				expediente.setDetalle(expedienteVO.getDetalle());
				expediente.setDocumentos(listaDocumento);//LIST
				expediente.setEntidadExterna(entidadExterna);//ENTITY
				expediente.setEstadoExpediente(estadoExpediente);//ENTITY
				expediente.setExpedienteDocPersonas(listaExpedienteDocPersona);//LIST
							
				
				expediente.setInteresado(expedienteVO.getInteresado());
				expediente.setNumeracionDocumento(numeracionDocumento);//entity
				expediente.setNumFolios(Integer.parseInt(expedienteVO.getNumFolios()));
				
				if(expedienteVO.getCodCorrelativo() == null ){
					/// AGREGAR
					expediente.setFecCreacion(new Date());
					//expedienteManager.add(expediente);
					
			        
					request.getSession().setAttribute("__Expediente_Session", expediente);
					
				}
				else{
					 /// EDITAR
					expediente.setCodUsuModificacion(expedienteVO.getCodUsuario());
					expediente.setFecModificacion(new Date());
					//expedienteManager.update(expediente);
				}
				
				GsonBuilder builder = new GsonBuilder();
		        Gson gson = builder.create();
		        
				return gson.toJson(expediente);
	}						
	
	
}
