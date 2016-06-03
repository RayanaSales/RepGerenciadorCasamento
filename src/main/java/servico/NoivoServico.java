package servico;

import entidades.Noivo;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import static javax.ejb.TransactionManagementType.CONTAINER;

@Stateless
@TransactionManagement(CONTAINER)
public class NoivoServico extends Servico
{
    public void salvar(Noivo noivo)
    {
       
        em.persist(noivo);
        
    }
}
