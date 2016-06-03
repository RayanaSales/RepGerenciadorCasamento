package servico;

import entidades.Telefone;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TelefoneServico extends Servico
{
    public void salvar(Telefone telefone)
    {
        
        
        em.persist(telefone);
        
        System.out.println("TelefoneServico DIZ: SALVEI O TELEFONE");
    }
}
