package servico;

import entidades.Buffet;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BuffetServico extends Servico
{
    public void salvar(Buffet buffet)
    {
        em.flush();
        em.persist(buffet);
        em.flush();
    }

    public List<Buffet> listar()
    {
        return em.createQuery("select t from Buffet AS t", Buffet.class).getResultList();
    }
    
    public void remover(Buffet buffet)
    {
        Buffet t = (Buffet) em.find(Buffet.class, buffet.getId()); //se n tiver isso, o jpa acha que n deatachou        
        em.remove(t);        
    }

    public void atualizar(Buffet buffet)
    {
        em.flush();
        em.merge(buffet);       
    }

    public boolean existente(Buffet buffet)
    {
        return listar().contains(buffet);
    }
}
