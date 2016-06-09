package servico;

import entidades.RoupaDosNoivos;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RoupaDosNoivosServico extends Servico
{

    public void salvar(RoupaDosNoivos roupa)
    {
        em.flush();
        em.persist(roupa);
    }

    public List<RoupaDosNoivos> listar()
    {
        return em.createQuery("select t from RoupaDosNoivos AS t", RoupaDosNoivos.class).getResultList();
    }

    public void remover(RoupaDosNoivos roupa)
    {
        RoupaDosNoivos r = (RoupaDosNoivos) em.find(RoupaDosNoivos.class, roupa.getId()); //se n tiver isso, o jpa acha que n deatachou        
        em.remove(r);
    }

    public void atualizar(RoupaDosNoivos roupa)
    {              
        em.flush();
        em.merge(roupa);
    }

    public boolean existente(RoupaDosNoivos roupa)
    {
        return listar().contains(roupa);
    }
}
