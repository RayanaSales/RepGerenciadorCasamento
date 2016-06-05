
package servico;

import entidades.Localizacao;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class LocalizacaoServico extends Servico {
    
    public void salvar(Localizacao localizacao){
        em.persist(localizacao);
    }
}
