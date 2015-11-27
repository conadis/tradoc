package pe.conadis.tradoc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.conadis.tradoc.dao.ExpedienteDAO;
import pe.conadis.tradoc.entity.Documento;
import pe.conadis.tradoc.entity.EstadosDocumento;
import pe.conadis.tradoc.entity.Expediente;
import pe.conadis.tradoc.entity.UnidadOrganica;
import pe.conadis.tradoc.entity.Usuario;
import pe.conadis.tradoc.vo.DocumentoVO;
import pe.conadis.tradoc.vo.ExpedienteVO;

@Repository
public class ExpedienteDAOImpl extends AbstractDAO<Expediente> implements ExpedienteDAO {


	@SuppressWarnings("unchecked")
	@Transactional
	public List<Expediente> searchListExpediente(ExpedienteVO expedienteVO) throws Exception{
		
		List<Expediente> listaExpediente = new ArrayList<Expediente>();
		
		listaExpediente = (List<Expediente>) this.getSessionFactory().getCurrentSession()
				.createQuery("from Expediente e "
						+ " where (e.numeracionDocumento.anioDocumento =:anioDocumento "
						+ " and e.numeracionDocumento.numDocumento =:numDocumento "
						+ " and e.estadoExpediente.codEstadoExpediente !=:codEstExpediente "
						+ " and e.numeracionDocumento.indExpediente =:indExpediente ) ")
				.setParameter("anioDocumento", expedienteVO.getAnioDocumento())
				.setParameter("numDocumento", Integer.parseInt(expedienteVO.getNumDocumento()))
				.setParameter("indExpediente", expedienteVO.getIndExpediente())
				.setParameter("codEstExpediente", 4)
				.list();
		return listaExpediente;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<DocumentoVO> listDocumentoExterno(Expediente expediente,String codUOusuario) throws Exception{
		
		StringBuilder strQuery = new StringBuilder();
		strQuery.append(" select CASE  WHEN de.COD_UO_ORIGEN IS NULL ");
		strQuery.append("        THEN (select ext.DES_ENTIDAD from ENTIDAD_EXTERNA ext where ext.COD_ENTIDAD = de.COD_ENTIDAD_ORIGEN) ");
		strQuery.append("        ELSE (select uot.DES_UO as xx from UNIDAD_ORGANICA uot where uot.COD_UO = de.COD_UO_ORIGEN) ");
		strQuery.append("        END as enviado_por, uo.DES_UO as dirigido_a, d.DES_NUM_DOCUMENTO, de.FEC_CREACION, ed.COD_EST_DOCUMENTO,ed.DES_EST_DOCUMENTO, ");
		strQuery.append(" d.COD_DOCUMENTO,d.COD_TIP_DOCUMENTO,d.COD_USU_CREACION,d.COD_USU_MODIFICACION,d.IND_DOC_EXTERNO,d.DESCRIPCION ");
		strQuery.append(" from EXPEDIENTE e,DOCUMENTO d,ESTADOS_DOCUMENTO ed,DERIVAR de,UNIDAD_ORGANICA uo ");		               
		strQuery.append(" where e.COD_EXPEDIENTE = d.COD_EXPEDIENTE and d.COD_EST_DOCUMENTO = ed.COD_EST_DOCUMENTO and ");
		strQuery.append(" d.COD_EST_DOCUMENTO <> 4 and d.IND_DOC_EXTERNO = '1' and d.COD_DOCUMENTO = de.COD_DOCUMENTO and ");		       
		strQuery.append(" de.COD_UO_DESTINO = uo.COD_UO ");
		strQuery.append(" AND de.COD_UO_ORIGEN ='" + codUOusuario +"' ");//UO del usuario logueado
		strQuery.append(" AND e.COD_EXPEDIENTE =" + expediente.getCodExpediente() + " ");//codigo de expediente
		strQuery.append(" order by d.COD_DOCUMENTO,de.COD_DERIVACION");
		
		List<Object[]> listObject =  this.getSessionFactory().getCurrentSession().createSQLQuery(strQuery.toString()).list();
		DocumentoVO documento = null;
		List<DocumentoVO> listaDocumento = new ArrayList<DocumentoVO>();
		for(Object[] obj : listObject){	
			documento = new DocumentoVO();
			documento.setDesEntidadExtUO(obj[0]!=null?obj[0].toString():null);
			documento.setDesUO(obj[1]!=null?obj[1].toString():null);
			documento.setDesNumDocumento(obj[2]!=null?obj[2].toString():null);
			documento.setFechaCreacion(obj[3]!=null?obj[3].toString():null);
			documento.setCodEstDocumento(obj[4]!=null?obj[4].toString():null);
			documento.setDesEstDocumento(obj[5]!=null?obj[5].toString():null);
			documento.setCodDocumento(obj[6]!=null?obj[6].toString():null);
			documento.setCodTipDocumento(obj[7]!=null?obj[7].toString():null);
			documento.setCodUsuCreacion(obj[8]!=null?obj[8].toString():null);
			documento.setCodUsuModificacion(obj[9]!=null?obj[9].toString():null);
			documento.setIndDocExterno(obj[10]!=null?obj[10].toString():null);
			documento.setDescripcion(obj[11]!=null?obj[11].toString():null);
			documento.setDocSession("N");
			listaDocumento.add(documento);
		}
		return listaDocumento;
	}
	
	//PROVICIONAL
	@SuppressWarnings("unchecked")
	@Transactional
	public List<UnidadOrganica> listUOporUsuario(Usuario usuario) throws Exception{
		
		List<UnidadOrganica> listaUnidadOrganica = new ArrayList<UnidadOrganica>();
		
		List<Object[]> listObject =  this.getSessionFactory().getCurrentSession()
				.createSQLQuery("SELECT UO.COD_UO,UO.DES_UO FROM FLUJO_COMUNICACION_UO FCUO INNER JOIN UNIDAD_ORGANICA UO "
						+ " ON     FCUO.COD_UO_DESTINO = UO.COD_UO "
						+ " INNER JOIN USUARIO U ON FCUO.COD_UO_ORIGEN = U.COD_UO "
						+ " WHERE  U.COD_USUARIO = '" + usuario.getCodUsuario() + "' AND UO.IND_ESTADO = '1'")
				.list();
		UnidadOrganica uo = null;
		for(Object[] obj : listObject){	
			uo = new UnidadOrganica();
			uo.setCodUo(Integer.parseInt(obj[0]!=null?obj[0].toString():null));
			uo.setDesUo(obj[1]!=null?obj[1].toString():null);
			listaUnidadOrganica.add(uo);
		}
		return listaUnidadOrganica;
	}
	
	
	
}
