package pe.conadis.tradoc.dao;

import pe.conadis.tradoc.entity.NumeracionDocumento;

public interface NumeracionDocumentoDAO extends Dao<NumeracionDocumento> {

	public Integer searchNextNumeroDocumento(NumeracionDocumento numeracionDocumento) throws Exception;
}
//