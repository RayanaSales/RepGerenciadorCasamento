package servico;

import entidades.Buffet;
import entidades.ComesBebes;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.TypedQuery;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ComesBebesServico extends Servico
{
    public void salvar(ComesBebes cb)
    {
        em.flush();
        
        if(existente(cb.getProduto()) == false)
            em.persist(cb);
    }

    public List<ComesBebes> listar()
    {
        return em.createQuery("select t from ComesBebes AS t", ComesBebes.class).getResultList();
    }

    public void remover(ComesBebes cb)
    {
        ComesBebes t = (ComesBebes) em.find(ComesBebes.class, cb.getId()); //se n tiver isso, o jpa acha que n deatachou        
        em.remove(t);
    }

    public void atualizar(ComesBebes cb)
    {              
        em.flush();
        em.merge(cb);
    }
    
    private boolean existente(String produto)
    {       
        TypedQuery<ComesBebes> query;
        query = em.createQuery("select b from ComesBebes b where b.produto like ?1", ComesBebes.class);
        query.setParameter(1, produto);
        List<ComesBebes> comesBebes = query.getResultList();

        if (comesBebes.isEmpty())
        {
            return false;
        }

        return true;
    }
    
}
