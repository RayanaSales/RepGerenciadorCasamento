package servico;

import entidades.Presente;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PresenteServico extends Servico
{
    public void salvar(Presente presente)
    {
        em.flush();
        em.persist(presente);
    }

    public List<Presente> listar()
    {
        return em.createQuery("select p from Presente AS p", Presente.class).getResultList();
    }

    public void remover(Presente presente)
    {
        Presente t = (Presente) em.find(Presente.class, presente.getId()); //se n tiver isso, o jpa acha que n deatachou        
        em.remove(t);
    }

    public void atualizar(Presente presente)
    {              
        em.flush();
        em.merge(presente);
    }

}
