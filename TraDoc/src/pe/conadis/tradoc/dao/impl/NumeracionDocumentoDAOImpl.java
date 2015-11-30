package pe.conadis.tradoc.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.conadis.tradoc.dao.NumeracionDocumentoDAO;
import pe.conadis.tradoc.entity.NumeracionDocumento;

@Repository
public class NumeracionDocumentoDAOImpl extends AbstractDAO<NumeracionDocumento> implements NumeracionDocumentoDAO {

	@Transactional
	public Integer searchNextNumeroDocumento(NumeracionDocumento numeracionDocumento) throws Exception{
		
		Integer numeroDocumento;
		
		numeroDocumento = (Integer) this.getSessionFactory().getCurrentSession()
				.createQuery("select max(nd.numDocumento) from NumeracionDocumento nd "
						+ " where (nd.anioDocumento =:anioDocumento "
						+ " and nd.indExpediente =:indExpediente "
						+ " and nd.indEstado =:indEstado ) ")
				.setParameter("anioDocumento", numeracionDocumento.getAnioDocumento())
				.setParameter("indExpediente", numeracionDocumento.getIndExpediente())
				.setParameter("indEstado", numeracionDocumento.getIndEstado())
				.uniqueResult();
		
		return numeroDocumento!=null?numeroDocumento+1:1;
	}	

}
