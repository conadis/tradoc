package pe.conadis.tradoc.service;

import pe.conadis.tradoc.entity.NumeracionDocumento;

public interface NumeracionDocumentoManager extends Service<NumeracionDocumento>{
	
	public Integer searchNextNumeroDocumento(NumeracionDocumento numeracionDocumento) throws Exception;

}
