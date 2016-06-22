package servico;

import entidades.Convidado;
import entidades.RoupaDosNoivos;
import excecao.ExcecaoNegocio;
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
public class RoupaDosNoivosServico extends Servico
{

    public void salvar(RoupaDosNoivos roupa) throws ExcecaoNegocio
    {
        em.flush();        
        if(existente(roupa.getRoupa()) == false)
            em.persist(roupa);
        else throw new ExcecaoNegocio(ExcecaoNegocio.OBJETO_EXISTENTE);  
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

    private boolean existente(String roupa)
    {       
        TypedQuery<RoupaDosNoivos> query;
        query = em.createQuery("select b from RoupaDosNoivos b where b.roupa like ?1", RoupaDosNoivos.class);
        query.setParameter(1, roupa);
        List<RoupaDosNoivos> pessoas = query.getResultList();

        if (pessoas.isEmpty())
        {
            return false;
        }

        return true;
    }
}
